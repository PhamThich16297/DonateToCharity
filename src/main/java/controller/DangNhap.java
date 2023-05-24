package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.TaiKhoan;
import dao.TaiKhoanDAO;
import mahoa.AES;

/**
 * Servlet implementation class DangNhap
 */
@WebServlet("/DangNhap")
public class DangNhap extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangNhap() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		// Ghi nhớ đăng nhập cho lần tiếp theo
		if(cookies != null) {
			for (Cookie cook : cookies) {
				if(cook.getName().equals("userCook")) {
					request.setAttribute("tendangnhap", cook.getValue());
				}
				if(cook.getName().equals("passCook")) {
					request.setAttribute("matkhau", cook.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
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
			String scret = "Aa@123456";
			// Lay du lieu tu nguoi dung nhap
			String tenDangNhap = request.getParameter("tendangnhap");
			String matKhau = request.getParameter("matkhau");
			String matKhauMH = AES.encrypt(matKhau, scret);

			String nhoMatKhau = request.getParameter("nhomatkhau");
			// Định dạng Tên Đăng Nhập và Mật Khẩu
			String regexTenDangNhap = "[a-zA-Z0-9]+";
			String regexMatKhau = "[a-zA-Z0-9_!@#$%^&*]+";

			
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			// Kiểm tra tài khoản đăng nhập
			TaiKhoan tkAdmin = tkDAO.checkDangNhap(tenDangNhap, matKhauMH, "1");
			TaiKhoan tkUser = tkDAO.checkDangNhap(tenDangNhap, matKhauMH, "0");
			
			
			// Lỗi để trống input
			if (tenDangNhap == "" || matKhau == "") {
				request.setAttribute("loidangnhap", "Nhập đầy đủ Tên Đăng Nhập và Mật Khẩu");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.getRequestDispatcher("DangNhap.jsp").forward(request, response);

				// Lỗi sai định dạng
			} else if (!tenDangNhap.matches(regexTenDangNhap) || !matKhau.matches(regexMatKhau)) {
				request.setAttribute("loidangnhap", "Sai cú pháp Tên Đăng Nhập hoặc Mật Khẩu");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
				// Đăng nhập thành công
			} else if (tkAdmin != null || tkUser != null) {
				
				HttpSession session = request.getSession();
				
				// Tạo Tên Đăng Nhập, Mật khẩu lên cookie
				Cookie u = new Cookie("userCook", tenDangNhap);
				Cookie p = new Cookie("passCook", matKhau);
				
				// Thời gian lưu 60s
				u.setMaxAge(60);
				if (nhoMatKhau != null) {
					p.setMaxAge(60);
				} else {
					p.setMaxAge(0);
				}

				// Luu cookie len client
				response.addCookie(u);
				response.addCookie(p);

				// Tài khoản admin
				if (tkAdmin != null) {
					// Gửi tenDangNhap lên session
					session.setAttribute("tkAdminSession", tkAdmin);
					response.sendRedirect("QuanTri.jsp");
				} 
				if (tkUser != null) {
					// Tài Khoản khách hàng
					if (tkUser.getTrangThai().equals("Không hoạt động")) {
						request.setAttribute("loidangnhap", "Tài Khoản đã bị vô hiệu hóa");
						request.setAttribute("tendangnhap", tenDangNhap);
						request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
					} else if (tkUser.getTrangThai().equals("Đang hoạt động")) {
						// Gửi tenDangNhap lên session
						session.setAttribute("tkUserSession", tkUser);
						response.sendRedirect("home");
					}
				}

				// Sai Tên Đăng Nhập hoặc Mật Khẩu
			} else {
				request.setAttribute("loidangnhap", "Tên Đăng Nhập hoặc Mật Khẩu không đúng");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.getRequestDispatcher("DangNhap.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
