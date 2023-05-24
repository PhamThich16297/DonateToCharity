package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.DVTN;
import dao.DVTNDAO;

/**
 * Servlet implementation class Sua_DVTN
 */
@MultipartConfig
@WebServlet("/Sua_DVTN")
public class Sua_DVTN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sua_DVTN() {
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
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {

				String maDVTN = request.getParameter("madvtn");
				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();
				DVTN dvtn = new DVTN();

				for (DVTN donvitiepnhan : listDVTN) {
					if (Integer.parseInt(maDVTN) == donvitiepnhan.getMaDVTN()) {
						dvtn = donvitiepnhan;
					}
				}
				request.setAttribute("dvtn", dvtn);
				request.getRequestDispatcher("Sua_DVTN.jsp").forward(request, response);
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
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {

				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();

				String maDVTN = request.getParameter("id") + "";
				String tenDVTN = request.getParameter("tendvtn") + "";

				Part part = request.getPart("imgLogo");
				String realPath = request.getServletContext().getRealPath("/images") + "";
				String filename = Path.of(part.getSubmittedFileName()).getFileName().toString() + "";
				String imgLogo = "";
				String thongTin = request.getParameter("thongtin") + "";

				DVTN dvtn = new DVTN();
				for (DVTN donViTiepNhan : listDVTN) {
					if (tenDVTN.equals(donViTiepNhan.getTenDVTN())) {
						request.setAttribute("loitendvtn", "Tên Đơn Vị Tiếp Nhận Đã Tồn Tại");
					}
				}
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectory(Path.of(realPath));
				}
				if (filename == "") {
					for (DVTN donViTiepNhan : listDVTN) {
						if (maDVTN.equals(donViTiepNhan.getMaDVTN() + "")) {
							imgLogo = donViTiepNhan.getUrlLogo() + "";
						}
					}
				} else {
					part.write(realPath + "/" + filename);
					imgLogo = "images/" + filename;
				}
				dvtn.setMaDVTN(Integer.parseInt(maDVTN));
				dvtn.setTenDVTN(tenDVTN);
				dvtn.setUrlLogo(imgLogo);
				dvtn.setThongTin(thongTin);

				if (tenDVTN == "" || thongTin == "") {
					if (tenDVTN == "") {
						request.setAttribute("loitendvtn", "Không được để trống");
					}
					if (thongTin == "") {
						request.setAttribute("loithongtin", "Không được để trống");
					}
					request.setAttribute("dvtn", dvtn);
					request.getRequestDispatcher("Sua_DVTN.jsp").forward(request, response);
				} else {
					dvtnDAO.updateDVTN(maDVTN, tenDVTN, imgLogo, thongTin);
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
