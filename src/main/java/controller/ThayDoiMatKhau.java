package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TaiKhoan;
import dao.TaiKhoanDAO;
import mahoa.AES;

/**
 * Servlet implementation class ThayDoiMatKhau
 */
@WebServlet("/ThayDoiMatKhau")
public class ThayDoiMatKhau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThayDoiMatKhau() {
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
			Object tkUserSession = session.getAttribute("tkUserSession");
			if (tkAdminSession != null || tkUserSession != null) {
				response.sendRedirect("ThayDoiMatKhau.jsp");
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
		try {

			String matKhauHienTai = request.getParameter("matkhauhientai");
			String matKhauMoi = request.getParameter("matkhaumoi");
			String matKhauMoi2 = request.getParameter("matkhaumoi2");
			String scret = "Aa@123456";
			String regexMatKhau = "[a-zA-Z0-9_!@#$%^&*]+";

			String matKhauHienTaiMH = AES.encrypt(matKhauHienTai, scret);
			String matKhauMoiMH = AES.encrypt(matKhauMoi, scret);
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();

			if (matKhauHienTai == "" || matKhauMoi == "" || matKhauMoi2 == "" || !matKhauMoi.matches(regexMatKhau)
					|| matKhauMoi.length() < 8) {
				if (matKhauHienTai == "" || matKhauMoi == "" || matKhauMoi2 == "") {
					request.setAttribute("loi", "Vui Lòng Nhập đầy đủ các trường");
				} else if (!matKhauMoi.matches(regexMatKhau) || matKhauMoi.length() < 8) {
					if (!matKhauMoi.matches(regexMatKhau)) {
						request.setAttribute("loi", "Sai cú pháp, kí tự nhập a-z,A-Z,0-9,_!@#$%^&*");
					} else {
						request.setAttribute("loi", "Mật Khẩu Mới phải có ít nhất 8 kí tự");
					}
				}
				request.setAttribute("matkhauhientai", matKhauHienTai);
				request.setAttribute("matkhaumoi", matKhauMoi);
				request.setAttribute("matkhaumoi2", matKhauMoi2);
				request.getRequestDispatcher("ThayDoiMatKhau.jsp").forward(request, response);
			} else if (!matKhauMoi.equals(matKhauMoi2)) {
				request.setAttribute("loi", "Mật Khẩu Mới không trùng khớp");
				request.setAttribute("matkhauhientai", matKhauHienTai);
				request.setAttribute("matkhaumoi", matKhauMoi);
				request.setAttribute("matkhaumoi2", matKhauMoi2);
				request.getRequestDispatcher("ThayDoiMatKhau.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();

				TaiKhoan tkAdmin = (TaiKhoan) session.getAttribute("tkAdminSession");
				TaiKhoan tkUser = (TaiKhoan) session.getAttribute("tkUserSession");

				if (tkUser != null) {
					if (matKhauHienTaiMH.equals(tkUser.getMatKhau())) {
						tkDAO.updatePassTK(tkUser.getTenDangNhap(), matKhauMoiMH);
						request.setAttribute("undo", "user");
						request.getRequestDispatcher("ThayDoiMatKhauSuccess.jsp").forward(request, response);
					} else if (!matKhauHienTaiMH.equals(tkUser.getMatKhau())) {
						request.setAttribute("loi", "Nhập Mật Khẩu Hiện Tại Không Đúng");
						request.setAttribute("matkhauhientai", matKhauHienTai);
						request.setAttribute("matkhaumoi", matKhauMoi);
						request.setAttribute("matkhaumoi2", matKhauMoi2);
						request.getRequestDispatcher("ThayDoiMatKhau.jsp").forward(request, response);
					}
				} else if (tkAdmin != null) {
					if (matKhauHienTaiMH.equals(tkAdmin.getMatKhau())) {
						tkDAO.updatePassTK(tkAdmin.getTenDangNhap(), matKhauMoiMH);
						request.setAttribute("undo", "admin");
						request.getRequestDispatcher("ThayDoiMatKhauSuccess.jsp").forward(request, response);
					} else if (!matKhauHienTaiMH.equals(tkAdmin.getMatKhau())) {
						request.setAttribute("loi", "Nhập Mật Khẩu Hiện Tại Không Đúng");
						request.setAttribute("matkhauhientai", matKhauHienTai);
						request.setAttribute("matkhaumoi", matKhauMoi);
						request.setAttribute("matkhaumoi2", matKhauMoi2);
						request.getRequestDispatcher("ThayDoiMatKhau.jsp").forward(request, response);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
