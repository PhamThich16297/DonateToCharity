package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.DVTNDAO;

/**
 * Servlet implementation class Them_DVTN
 */
@MultipartConfig
@WebServlet("/Them_DVTN")
public class Them_DVTN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Them_DVTN() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				response.sendRedirect("Them_DVTN.jsp");
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String tenDVTN = request.getParameter("tendvtn") + "";

				Part part = request.getPart("imgLogo");
				String realPath = request.getServletContext().getRealPath("/images");
				String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectory(Path.of(realPath));
				}

				String thongTin = request.getParameter("thongtin") + "";
				String imgLogo = "";
				if (tenDVTN == "" || filename == "" || thongTin == "") {
					if (tenDVTN == "") {
						request.setAttribute("loitendvtn", "Chưa Nhập Tên Đơn Vị Tiếp Nhận");
					} else {
						request.setAttribute("valtendvtn", tenDVTN);
					}
					if (thongTin == "") {
						request.setAttribute("loithongtin", "Chưa Nhập Thông Tin");
					} else {
						request.setAttribute("valthongtin", thongTin);
					}

					if (filename == "") {
						request.setAttribute("loiimglogo", "Chưa chọn Hình ảnh Logo");
					}

					request.getRequestDispatcher("Them_DVTN.jsp").forward(request, response);
				} else {
					part.write(realPath + "/" + filename);
					imgLogo = "images/" + filename;
					DVTNDAO dvtnDAO = new DVTNDAO();
					dvtnDAO.insertDVTN(tenDVTN, thongTin, imgLogo);
					response.sendRedirect("XemDS_DVTN");
				}
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
