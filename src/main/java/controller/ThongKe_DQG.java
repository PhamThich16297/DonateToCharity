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
import bean.TaiKhoan;
import dao.DotQuyenGopDAO;
import dao.QuyenGop1LanDAO;

/**
 * Servlet implementation class ThongKe_DQG
 */
@WebServlet("/ThongKe_DQG")
public class ThongKe_DQG extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThongKe_DQG() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			TaiKhoan tkAdminSession = (TaiKhoan)session.getAttribute("tkAdminSession");
			if(tkAdminSession != null) {
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
				
				String pageStr = request.getParameter("page");
				int page;
				if (pageStr == null) {
					page = 1;
				} else {
					page = Integer.parseInt(pageStr);
				}
				int countPage = dqgDAO.getCountPage();
				int countAll_DQG = dqgDAO.getListAllDQG().size();
				int countDDR_DQG = dqgDAO.getListDangDienRaDQG().size();
				int countKT_DQG = dqgDAO.getListKetThucDQG().size();
				int countLuotAll = qg1lDAO.getTongLuotDaQuyenGop();
				float countTienAll = qg1lDAO.getTienDaQuyenGop();
				List<DotQuyenGop> listDQG = dqgDAO.getListOfPage(page);
				
				request.setAttribute("page", page);
				request.setAttribute("countPage", countPage);
				request.setAttribute("countAll_DQG", countAll_DQG);
				request.setAttribute("countDDR_DQG", countDDR_DQG);
				request.setAttribute("countKT_DQG", countKT_DQG);
				request.setAttribute("countLuotAll", countLuotAll);
				request.setAttribute("countTienAll", countTienAll);
				request.setAttribute("listDQG", listDQG);
				request.getRequestDispatcher("ThongKe_DQG.jsp").forward(request, response);
				
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			TaiKhoan tkAdminSession = (TaiKhoan)session.getAttribute("tkAdminSession");
			if(tkAdminSession != null) {
				String trangThai = request.getParameter("trangthai");
				String luotQuyenGop = request.getParameter("luotquyengop");
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> list = new ArrayList<>();
				QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
				int countAll_DQG = dqgDAO.getListAllDQG().size();
				int countDDR_DQG = dqgDAO.getListDangDienRaDQG().size();
				int countKT_DQG = dqgDAO.getListKetThucDQG().size();
				int countLuotAll = qg1lDAO.getTongLuotDaQuyenGop();
				float countTienAll = qg1lDAO.getTienDaQuyenGop();
				if(trangThai.equals("tatca")) {
					List<DotQuyenGop> listAllDQG = dqgDAO.getListAllDQG();
					request.setAttribute("TTtatca", "selected");
					if(luotQuyenGop.equals("tang")) {
						list = dqgDAO.getListDQGTangLuot(listAllDQG);
						request.setAttribute("luottang", "selected");
					} else if(luotQuyenGop.equals("giam")) {
						list = dqgDAO.getListDQGGiamLuot(listAllDQG);
						request.setAttribute("luotgiam", "selected");
					}
				} else if(trangThai.equals("dangdienra")){
					List<DotQuyenGop> listDDRDQG = dqgDAO.getListDangDienRaDQG();
					request.setAttribute("TTdangdienra", "selected");
					if(luotQuyenGop.equals("tang")) {
						list = dqgDAO.getListDQGTangLuot(listDDRDQG);
						request.setAttribute("luottang", "selected");
					} else if(luotQuyenGop.equals("giam")) {
						list = dqgDAO.getListDQGGiamLuot(listDDRDQG);
						request.setAttribute("luotgiam", "selected");
					}
				} else if(trangThai.equals("daketthuc")) {
					List<DotQuyenGop> listDKTDQG = dqgDAO.getListKetThucDQG();
					request.setAttribute("TTdaketthuc", "selected");
					if(luotQuyenGop.equals("tang")) {
						list = dqgDAO.getListDQGGiamLuot(listDKTDQG);
						request.setAttribute("luottang", "selected");
					} else if(luotQuyenGop.equals("giam")) {
						list = dqgDAO.getListDQGGiamLuot(listDKTDQG);
						request.setAttribute("luotgiam", "selected");
					}
				}
				request.setAttribute("countAll_DQG", countAll_DQG);
				request.setAttribute("countDDR_DQG", countDDR_DQG);
				request.setAttribute("countKT_DQG", countKT_DQG);
				request.setAttribute("countLuotAll", countLuotAll);
				request.setAttribute("countTienAll", countTienAll);
				request.setAttribute("listDQG", list);
				request.getRequestDispatcher("ThongKe_DQG.jsp").forward(request, response);
				
			}else {
				response.sendRedirect("DangNhap.jsp");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
