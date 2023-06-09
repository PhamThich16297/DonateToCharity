package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.DotQuyenGop;
import dao.DotQuyenGopDAO;

/**
 * Servlet implementation class ChiTietDotQuyenGop
 */
@WebServlet("/ChiTietDotQuyenGop")
public class ChiTietDotQuyenGop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTietDotQuyenGop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			String maDot = request.getParameter("madot");
			DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
			DotQuyenGop dqg = dqgDAO.getDQG(Integer.parseInt(maDot));
			request.setAttribute("dotquyengop", dqg);
			request.getRequestDispatcher("ChiTietDotQuyenGop.jsp").forward(request, response);
			
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
