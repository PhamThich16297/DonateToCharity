package controller;

import java.io.IOException;
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
 * Servlet implementation class xoaNhieuDQG
 */
@WebServlet("/XoaNhieu_DQG")
public class XoaNhieu_DQG extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XoaNhieu_DQG() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String[] chonNhieu = request.getParameterValues("chon");
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				List<DotQuyenGop> listDQG = dqgDAO.getListAllDQG();
				if (chonNhieu == null) {
					request.setAttribute("thongbaoxoa", "Chưa chọn mục để xóa");
				} else {
					for (String item : chonNhieu) {
						for (DotQuyenGop dqg : listDQG) {
							if (item.equals(String.valueOf(dqg.getMaDot()))) {
								dqgDAO.deleteDQG(item);
							}
						}
					}
					request.setAttribute("thongbaoxoa", "Xóa Thành Công");
				}
				request.getRequestDispatcher("XemDS_DQG").forward(request, response);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
