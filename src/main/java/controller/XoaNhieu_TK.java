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
 * Servlet implementation class XoaNhieu_TK
 */
@WebServlet("/XoaNhieu_TK")
public class XoaNhieu_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XoaNhieu_TK() {
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
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListAllTK();
				List<TaiKhoan> listTest = new ArrayList<>();
				boolean b = false;
				for (String item : chonNhieu) {
					for (TaiKhoan tk : listTK) {
						if (item.equals(tk.getTenDangNhap())) {
							listTest.add(tk);
						}
					}
				}
				for (TaiKhoan tk : listTest) {
					if (tk.getPhanQuyen().equals("1")) {
						b = true;
						request.setAttribute("thongbaoxoa", "Xóa Thất Bại, Không thể XÓA Admin");
						break;
					}
				}
				if (!b) {
					for (TaiKhoan tk : listTK) {
						for (String item : chonNhieu) {
							if (item.equals(tk.getTenDangNhap())) {
								tkDAO.deleteTK(item);
							}
						}
					}
					request.setAttribute("thongbaoxoa", "Xóa Thành Công");
				}
				request.getRequestDispatcher("XemDS_TK").forward(request, response);
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
