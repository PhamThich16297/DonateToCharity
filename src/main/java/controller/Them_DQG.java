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
import dao.DotQuyenGopDAO;

/**
 * Servlet implementation class ThemDQG
 */
@MultipartConfig
@WebServlet("/Them_DQG")
public class Them_DQG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Them_DQG() {
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
				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();
				request.setAttribute("listDVTN", listDVTN);
				request.getRequestDispatcher("Them_DQG.jsp").forward(request, response);
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
				String tieuDe = request.getParameter("tieude") + "";

				Part part = request.getPart("imgBanner");
				String realPath = request.getServletContext().getRealPath("/images");
				String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectory(Path.of(realPath));
				}

				String tienQuyenGop = request.getParameter("tienquyengop") + "";

				String tenDVTN = request.getParameter("tendvtn");
				String maDVTN = "";

				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();

				for (DVTN dvtn : listDVTN) {
					if (dvtn.getTenDVTN().equals(tenDVTN)) {
						maDVTN = String.valueOf(dvtn.getMaDVTN());
					}
				}

				String ngayBD = request.getParameter("ngayBD") + "";
				String ngayKT = request.getParameter("ngayKT") + "";
				String noiDung = request.getParameter("noidung") + "";
				String imgBanner = "";
				if (tieuDe == "" || filename == "" || tienQuyenGop == "" || tenDVTN.equals("Đơn Vị Tiếp Nhận")
						|| ngayBD == "" || ngayKT == "" || ngayBD.compareTo(ngayKT) >= 0 || noiDung == "") {
					if (tieuDe == "") {
						request.setAttribute("tieuDe", "Chưa Nhập tiêu Đề");
					} else {
						request.setAttribute("valtieuDe", tieuDe);
					}
					if (noiDung == "") {
						request.setAttribute("noiDung", "Chưa Nhập Nội Dung");
					} else {
						request.setAttribute("valnoiDung", noiDung);
					}

					if (filename == "") {
						request.setAttribute("imgBanner", "Chưa Nhập img");
					}
					if (tienQuyenGop == "") {
						request.setAttribute("tienQuyenGop", "Chưa Nhập Tiền");
					} else {
						request.setAttribute("valtienQuyenGop", tienQuyenGop);
					}
					if (tenDVTN.equals("Đơn Vị Tiếp Nhận")) {
						request.setAttribute("donviTN", "Chưa Nhập DVTN");
					} else {
						request.setAttribute("valdonviTN", tenDVTN);
					}

					if (ngayBD == "" || ngayKT == "" || ngayBD.compareTo(ngayKT) >= 0) {
						if (ngayBD == "" || ngayKT == "") {
							if (ngayBD == "") {
								request.setAttribute("thoigianBD", "Chưa Nhập Ngày Bắt Đầu");
							} else {
								request.setAttribute("valthoigianBD", ngayBD);
							}
							if (ngayKT == "") {
								request.setAttribute("thoigianKT", "Chưa Nhập Ngày Kết Thúc");
							} else {
								request.setAttribute("valthoigianKT", ngayKT);
							}

						} else {
							request.setAttribute("thoigian", "Ngày Kết Thúc phải sau Ngày Bắt Đầu");
							request.setAttribute("valthoigianBD", ngayBD);
							request.setAttribute("valthoigianKT", ngayKT);
						}
					} else {
						request.setAttribute("valthoigianBD", ngayBD);
						request.setAttribute("valthoigianKT", ngayKT);
					}
					request.setAttribute("listDVTN", listDVTN);
					request.getRequestDispatcher("Them_DQG.jsp").forward(request, response);
				} else {
					part.write(realPath + "/" + filename);
					imgBanner = "images/" + filename;

					DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
					dqgDAO.insertDQG(tieuDe, ngayBD, ngayKT, tienQuyenGop, imgBanner, maDVTN, noiDung);
					response.sendRedirect("XemDS_DQG");
				}
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
