package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DotQuyenGop;
import dao.DotQuyenGopDAO;

/**
 * Servlet implementation class DanhSachDotQuyenGop
 */
@WebServlet("/XemDS_DQG")
public class XemDS_DQG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XemDS_DQG() {
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
				String pageStr = request.getParameter("page");
				int page;
				if (pageStr == null) {
					page = 1;
				} else {
					page = Integer.parseInt(pageStr);
				}
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> list = dqgDAO.getListOfPage(page);
				int countPage = dqgDAO.getCountPage();

				request.setAttribute("page", page);
				request.setAttribute("countPage", countPage);
				request.setAttribute("list", list);
				request.getRequestDispatcher("XemDS_DQG.jsp").forward(request, response);
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
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String phanLoai = request.getParameter("phanloai");
				String sapXep = request.getParameter("sapxep");
				String tangGiam = request.getParameter("tanggiam");

				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> list = new ArrayList<>();

				if (phanLoai.equals("PLtatca")) {
					List<DotQuyenGop> listAll = dqgDAO.getListAllDQG();
					request.setAttribute("PLtatca", "selected");
					if (sapXep.equals("SXtheoID")) {
						request.setAttribute("SXtheoID", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangID(listAll);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamID(listAll);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheotien")) {
						request.setAttribute("SXtheotien", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangTheoTien(listAll);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamTheoTien(listAll);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheoTGian")) {
						request.setAttribute("SXtheoTGian", "selected");
//					if (tangGiam.equals("tang")) {
//						list = dqgDAO.getListDQGTangTheoTGian(listAll);
//						request.setAttribute("tang", "selected");
//					} else if (tangGiam.equals("giam")) {
//						list = dqgDAO.getListDQGGiamTheoTGian(listAll);
//						request.setAttribute("giam", "selected");
//					}
					}
				} else if (phanLoai.equals("PLdangdienra")) {
					List<DotQuyenGop> listDangDienRa = dqgDAO.getListDangDienRaDQG();
					request.setAttribute("PLdangdienra", "selected");
					if (sapXep.equals("SXtheoID")) {
						request.setAttribute("SXtheoID", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangID(listDangDienRa);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamID(listDangDienRa);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheotien")) {
						request.setAttribute("SXtheotien", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangTheoTien(listDangDienRa);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamTheoTien(listDangDienRa);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheoTGian")) {
						request.setAttribute("SXtheoTGian", "selected");
					}
				} else if (phanLoai.equals("PLdaketthuc")) {
					List<DotQuyenGop> listDaKetThuc = dqgDAO.getListKetThucDQG();
					request.setAttribute("PLdaketthuc", "selected");
					if (sapXep.equals("SXtheoID")) {
						request.setAttribute("SXtheoID", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangID(listDaKetThuc);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamID(listDaKetThuc);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheotien")) {
						request.setAttribute("SXtheotien", "selected");
						if (tangGiam.equals("tang")) {
							list = dqgDAO.getListDQGTangTheoTien(listDaKetThuc);
							request.setAttribute("tang", "selected");
						} else if (tangGiam.equals("giam")) {
							list = dqgDAO.getListDQGGiamTheoTien(listDaKetThuc);
							request.setAttribute("giam", "selected");
						}
					} else if (sapXep.equals("SXtheoTGian")) {
						request.setAttribute("SXtheoTGian", "selected");
					}
				}

				request.setAttribute("list", list);
				request.getRequestDispatcher("XemDS_DQG.jsp").forward(request, response);
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
