package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Email;
import bean.TaiKhoan;
import dao.TaiKhoanDAO;
import mahoa.AES;
import mailcommon.GuiEmail;

/**
 * Servlet implementation class QuenMatKhau
 */
@WebServlet("/QuenMatKhau")
public class QuenMatKhau extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuenMatKhau() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			String tenDangNhap = request.getParameter("tendangnhap");
			String email = request.getParameter("email");
			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			TaiKhoan taiKhoan = tkDAO.getTaiKhoan(tenDangNhap, email);
//^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$
			String regexMail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";


			if (tenDangNhap == "" || email == "") {
				request.setAttribute("loi", "Vui lòng nhập đầy đủ Tên Đăng Nhập và Địa Chỉ Email");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.setAttribute("email", email);
				request.getRequestDispatcher("QuenMatKhau.jsp").forward(request, response);
			} else if (!email.matches(regexMail)) {
				request.setAttribute("loi", "Sai cú pháp Email");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.setAttribute("email", email);
				request.getRequestDispatcher("QuenMatKhau.jsp").forward(request, response);
			} else if (taiKhoan == null) {
				request.setAttribute("loi", "Tên Đăng Nhập hoặc Email không đúng");
				request.setAttribute("tendangnhap", tenDangNhap);
				request.setAttribute("email", email);
				request.getRequestDispatcher("QuenMatKhau.jsp").forward(request, response);
			} else {
				Email e = new Email();
				e.setGuiTu("thichpfx13302@funix.edu.vn");
				e.setGuiMatKhau("T5dYcX7DMbMV");
				e.setGuiDen(email);
				e.setTieuDeMail("Quên Mật Khẩu");
				
				String scret = "Aa@123456";
				Random rd = new Random();
				BigInteger matKhauRandom = new BigInteger(32, rd);
				String matKhauMH = AES.encrypt(String.valueOf(matKhauRandom), scret);
				tkDAO.updatePassTK(tenDangNhap, matKhauMH);
				
				StringBuilder sb = new StringBuilder();
				sb.append("Gửi ").append(tenDangNhap).append("<br>");
				sb.append("Mật Khẩu của Bạn là: <b>").append(matKhauRandom).append("<b>");
				e.setNoiDung(sb.toString());
				GuiEmail.send(e);
				response.sendRedirect("QuenMatKhauSuccess.jsp");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
