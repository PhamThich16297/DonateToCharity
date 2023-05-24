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

import bean.QuyenGop1Lan;
import bean.TaiKhoan;
import dao.QuyenGop1LanDAO;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class ThongKe_TK
 */
@WebServlet("/ThongKe_TK")
public class ThongKe_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThongKe_TK() {
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
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
				
				String pageStr = request.getParameter("page");
				int page;
				if (pageStr == null) {
					page = 1;
				} else {
					page = Integer.parseInt(pageStr);
				}
				int countPage = qg1lDAO.getCountPageTKUser();
				int countAll_TK = tkDAO.getListUser().size();
				int countDangHD_TK = tkDAO.getListTK_DangHD().size();
				int countKhongHD_TK = tkDAO.getListTK_KhongHD().size();
				List<QuyenGop1Lan> listUser = qg1lDAO.getListTKUserOfPage(page);
				
				request.setAttribute("page", page);
				request.setAttribute("countPage", countPage);
				request.setAttribute("countAll_TK", countAll_TK);
				request.setAttribute("countDangHD_TK", countDangHD_TK);
				request.setAttribute("countKhongHD_TK", countKhongHD_TK);
				request.setAttribute("listUser", listUser);
				request.getRequestDispatcher("ThongKe_TK.jsp").forward(request, response);
				
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		}catch (Exception e) {
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
				String thamGiaQG = request.getParameter("thamgiaQG");
				QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<QuyenGop1Lan> listQG1L_All = qg1lDAO.getListTKUser();
				List<QuyenGop1Lan> listQG1L_HD = qg1lDAO.getListDHD(listQG1L_All);
				List<QuyenGop1Lan> listQG1L_KHD = qg1lDAO.getListKHD(listQG1L_All);
				List<QuyenGop1Lan> list = new ArrayList<>();
				if(trangThai.equals("TTtatca")) {
					request.setAttribute("TTtatca", "selected");
					if(thamGiaQG.equals("TGtatca")) {
						request.setAttribute("tatca", "selected");
						list = listQG1L_All;
					} else if(thamGiaQG.equals("TGco")) {
						request.setAttribute("dathamgia", "selected");
						list = qg1lDAO.getListDaThamGia(listQG1L_All);
					} else if(thamGiaQG.equals("TGkhong")) {
						request.setAttribute("chuathamgia", "selected");
						list = qg1lDAO.getListChuaThamGia(listQG1L_All);
					}
				} else if(trangThai.equals("TThoatdong")) {
					request.setAttribute("TThoatdong", "selected");
					if(thamGiaQG.equals("TGtatca")) {
						request.setAttribute("tatca", "selected");
						list = listQG1L_HD;
					} else if(thamGiaQG.equals("TGco")) {
						request.setAttribute("dathamgia", "selected");
						list = qg1lDAO.getListDaThamGia(listQG1L_HD);
					} else if(thamGiaQG.equals("TGkhong")) {
						request.setAttribute("chuathamgia", "selected");
						list = qg1lDAO.getListChuaThamGia(listQG1L_HD);
					}
				} else if(trangThai.equals("TTkhonghoatdong")) {
					request.setAttribute("TTkhonghoatdong", "selected");
					if(thamGiaQG.equals("TGtatca")) {
						request.setAttribute("tatca", "selected");
						list = listQG1L_KHD;
					} else if(thamGiaQG.equals("TGco")) {
						request.setAttribute("dathamgia", "selected");
						list = qg1lDAO.getListDaThamGia(listQG1L_KHD);
					} else if(thamGiaQG.equals("TGkhong")) {
						request.setAttribute("chuathamgia", "selected");
						list = qg1lDAO.getListChuaThamGia(listQG1L_KHD);
					}
				}
				int countAll_TK = tkDAO.getListUser().size();
				int countDangHD_TK = tkDAO.getListTK_DangHD().size();
				int countKhongHD_TK = tkDAO.getListTK_KhongHD().size();
				request.setAttribute("countAll_TK", countAll_TK);
				request.setAttribute("countDangHD_TK", countDangHD_TK);
				request.setAttribute("countKhongHD_TK", countKhongHD_TK);
				request.setAttribute("listUser", list);
				request.getRequestDispatcher("ThongKe_TK.jsp").forward(request, response);
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
