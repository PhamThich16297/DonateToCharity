package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
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
import bean.DotQuyenGop;
import dao.DVTNDAO;
import dao.DotQuyenGopDAO;

/**
 * Servlet implementation class suaDQG
 */
@MultipartConfig
@WebServlet("/Sua_DQG")
public class Sua_DQG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sua_DQG() {
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
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {

				DecimalFormat dcf = new DecimalFormat("#.##");
				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();

				String id = request.getParameter("id");
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> listDQG = dqgDAO.getListAllDQG();
				DotQuyenGop dotQuyenGop = new DotQuyenGop();
				for (DotQuyenGop dqg : listDQG) {
					if (id.equals(String.valueOf(dqg.getMaDot()))) {
						dotQuyenGop = dqg;
						request.setAttribute("dotQuyenGop", dotQuyenGop);
						String tienQG = dcf.format(dotQuyenGop.getTienQuyenGop());
						request.setAttribute("tienQG", tienQG);
						request.setAttribute("valngayBD", dotQuyenGop.getNgayBatDau());
						request.setAttribute("valngayKT", dotQuyenGop.getNgayKetThuc());
					}
				}

				request.setAttribute("selected", "selected");
				request.setAttribute("listDVTN", listDVTN);
				request.getRequestDispatcher("Sua_DQG.jsp").forward(request, response);
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
			Object tkSession = session.getAttribute("tkAdminSession");
			if (tkSession != null) {
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> listDQG = dqgDAO.getListAllDQG();

				String id = request.getParameter("id") + "";
				String tieuDe = request.getParameter("tieude") + "";
				String tienQuyenGop = request.getParameter("tienquyengop") + "";

				Part part = request.getPart("imgBanner");
				String realPath = request.getServletContext().getRealPath("/images") + "";
				String filename = Path.of(part.getSubmittedFileName()).getFileName().toString() + "";
				String imgBanner = "";
				String tenDVTN = request.getParameter("tendvtn") + "";
				String maDVTN = "";
				String ngayBD = request.getParameter("ngayBD") + "";
				String ngayKT = request.getParameter("ngayKT") + "";
				String noiDung = request.getParameter("noidung") + "";

				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListDVTN();

				DVTN donViTiepNhan = new DVTN();
				for (DVTN dvtn : listDVTN) {
					if (dvtn.getTenDVTN().equals(tenDVTN)) {
						maDVTN = dvtn.getMaDVTN() + "";
						donViTiepNhan = dvtn;
					}
				}
				if (!Files.exists(Path.of(realPath))) {
					Files.createDirectory(Path.of(realPath));
				}
				DotQuyenGop dotQuyenGop = new DotQuyenGop();
				if (filename == "") {
					for (DotQuyenGop dqg : listDQG) {
						if (id.equals(dqg.getMaDot() + "")) {
							imgBanner = dqg.getUrlBanner() + "";
							dotQuyenGop = dqg;
						}
					}
				} else {
					part.write(realPath + "/" + filename);
					imgBanner = "images/" + filename;
				}
				dotQuyenGop.setTieuDe(tieuDe);
				dotQuyenGop.setNoiDung(noiDung);
				dotQuyenGop.setUrlBanner(imgBanner);

				dotQuyenGop.setDvtn(donViTiepNhan);
//			dotQuyenGop.setNgayBatDau(new SimpleDateFormat("yyyy-MM-dd").parse(ngayBD));
//			dotQuyenGop.setNgayKetThuc(new SimpleDateFormat("yyyy-MM-dd").parse(ngayKT));
				if (tieuDe == "" || tienQuyenGop == "" || tenDVTN.equals("Đơn Vị Tiếp Nhận") || ngayBD == ""
						|| ngayKT == "" || ngayBD.compareTo(ngayKT) >= 0 || noiDung == "") {
					if (tieuDe == "") {
						request.setAttribute("tieuDe", "Không được để trống");

					}
					if (noiDung == "") {
						request.setAttribute("noiDung", "Không được để trống");
					}

					if (tienQuyenGop == "") {
						request.setAttribute("tienQuyenGop", "Không được để trống");
					} else {
						dotQuyenGop.setTienQuyenGop(Float.parseFloat(tienQuyenGop));
					}

					if (tenDVTN.equals("Đơn Vị Tiếp Nhận")) {
						request.setAttribute("donviTN", "Chưa Chọn Đơn Vị Tiếp Nhận");
					}

					if (ngayBD == "" || ngayKT == "" || ngayBD.compareTo(ngayKT) >= 0) {
						if (ngayBD == "" || ngayKT == "") {
							if (ngayBD == "") {
								request.setAttribute("thoigianBD", "Chưa Nhập Ngày Bắt Đầu");
							} else {
								request.setAttribute("valngayBD", ngayBD);
							}
							if (ngayKT == "") {
								request.setAttribute("thoigianKT", "Chưa Nhập Ngày Kết Thúc");
							} else {
								request.setAttribute("valngayKT", ngayKT);
							}

						} else {
							request.setAttribute("thoigian", "ĐỊnh dạng ngày sai");
							request.setAttribute("valngayBD", ngayBD);
							request.setAttribute("valngayKT", ngayKT);
						}
					} else {
						request.setAttribute("valngayBD", ngayBD);
						request.setAttribute("valngayKT", ngayKT);
					}

					request.setAttribute("dotQuyenGop", dotQuyenGop);
					request.setAttribute("listDVTN", listDVTN);

					request.setAttribute("tienQG", tienQuyenGop);
					request.getRequestDispatcher("Sua_DQG.jsp").forward(request, response);
				} else {
					dqgDAO.updateDQG(id, tieuDe, ngayBD, ngayKT, tienQuyenGop, imgBanner, maDVTN, noiDung);
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
