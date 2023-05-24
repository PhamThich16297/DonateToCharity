package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DVTN;
import dao.DVTNDAO;

/**
 * Servlet implementation class XemDS_DVTN
 */
@WebServlet("/XemDS_DVTN")
public class XemDS_DVTN extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XemDS_DVTN() {
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
				String pageStr = request.getParameter("pageDVTN");
				int pageDVTN;
				if (pageStr == null) {
					pageDVTN = 1;
				} else {
					pageDVTN = Integer.parseInt(pageStr);
				}
				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.getListOfPage(pageDVTN);
				int countPageDVTN = dvtnDAO.getCountPage();

				request.setAttribute("pageDVTN", pageDVTN);
				request.setAttribute("countPageDVTN", countPageDVTN);
				request.setAttribute("listDVTN", listDVTN);
				request.getRequestDispatcher("XemDS_DVTN.jsp").forward(request, response);
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String search = request.getParameter("search");
				DVTNDAO dvtnDAO = new DVTNDAO();
				List<DVTN> listDVTN = dvtnDAO.searchDVTN(search);
				if(listDVTN == null) {
					request.setAttribute("search", search);
					request.setAttribute("ketqua", "Không có kết quả");
				} else {
					request.setAttribute("search", search);
					request.setAttribute("listDVTN", listDVTN);
				}
				request.getRequestDispatcher("XemDS_DVTN.jsp").forward(request, response);
			}else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
