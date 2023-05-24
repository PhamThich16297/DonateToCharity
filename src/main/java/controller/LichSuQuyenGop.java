package controller;

import java.io.IOException;
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

/**
 * Servlet implementation class LichSuQuyenGop
 */
@WebServlet("/LichSuQuyenGop")
public class LichSuQuyenGop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichSuQuyenGop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			TaiKhoan tkUserSession = (TaiKhoan)session.getAttribute("tkUserSession");
			if(tkUserSession != null) {
				QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
				List<QuyenGop1Lan> listQG1L = qg1lDAO.getListQuyenGop1Lan(tkUserSession.getTenDangNhap());
				request.setAttribute("listQG1L", listQG1L);
				request.setAttribute("TaiKhoan", tkUserSession);
				request.getRequestDispatcher("LichSuQG_TK.jsp").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
