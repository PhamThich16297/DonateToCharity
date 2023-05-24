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

import bean.TaiKhoan;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class XenDS_TK
 */
@WebServlet("/XemDS_TK")
public class XemDS_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XemDS_TK() {
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
				String pageTKStr = request.getParameter("pageTK");
				int pageTK;
				if (pageTKStr == null) {
					pageTK = 1;
				} else {
					pageTK = Integer.parseInt(pageTKStr);
				}
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListTKOfPage(pageTK);
				int countPageTK = tkDAO.getCountPageTK();

				request.setAttribute("pageTK", pageTK);
				request.setAttribute("countPageTK", countPageTK);
				request.setAttribute("listTK", listTK);
				request.getRequestDispatcher("XemDS_TK.jsp").forward(request, response);
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
				String search = request.getParameter("search");

				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = new ArrayList<>();

				if (phanLoai.equals("PLtatca")) {
					request.setAttribute("PLtatca", "selected");
					if (sapXep.equals("SXtheoten")) {
						request.setAttribute("SXtheoten", "selected");
						listTK = tkDAO.getListAllTenDangNhap(search);
					} else if (sapXep.equals("SXtheosdt")) {
						request.setAttribute("SXtheosdt", "selected");
						listTK = tkDAO.getListAllSdt(search);
					}

				} else if (phanLoai.equals("PLadmin")) {
					request.setAttribute("PLadmin", "selected");
					if (sapXep.equals("SXtheoten")) {
						request.setAttribute("SXtheoten", "selected");
						listTK = tkDAO.getListAdminTenDangNhap(search);
					} else if (sapXep.equals("SXtheosdt")) {
						request.setAttribute("SXtheosdt", "selected");
						listTK = tkDAO.getListAdminSdt(search);
					}

				} else if (phanLoai.equals("PLuser")) {
					request.setAttribute("PLuser", "selected");
					if (sapXep.equals("SXtheoten")) {
						request.setAttribute("SXtheoten", "selected");
						listTK = tkDAO.getListUserTenDangNhap(search);
					} else if (sapXep.equals("SXtheosdt")) {
						request.setAttribute("SXtheosdt", "selected");
						listTK = tkDAO.getListUserSdt(search);
					}
				}
				request.setAttribute("search", search);
				request.setAttribute("listTK", listTK);
				request.getRequestDispatcher("XemDS_TK.jsp").forward(request, response);

			} else {
				response.sendRedirect("DangNhap.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
