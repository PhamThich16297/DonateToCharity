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
import dao.TaiKhoanDAO;
import mahoa.AES;
import mailcommon.GuiEmail;

/**
 * Servlet implementation class DangKy
 */
@WebServlet("/DangKy")
public class DangKy extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKy() {
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
		request.setCharacterEncoding("utf-8");
		try {
			String regexTenDangNhap = "[a-zA-Z0-9]+";
			String regexMail = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
			String regexSDT = "[0-9]+";
			String scret = "Aa@123456";

			String tenDangNhap = request.getParameter("tendangnhap");
			String email = request.getParameter("email");
			String tenCaNhan = request.getParameter("tencanhan");
			String diaChi = request.getParameter("diachi");
			String sdt = request.getParameter("sdt");

			TaiKhoanDAO tkDAO = new TaiKhoanDAO();
			boolean bTenDangNhap = tkDAO.booleanTKvsTenDangNhap(tenDangNhap);
			boolean bEmail = tkDAO.booleanTKvsEmail(email);


			if (tenDangNhap == "" || email == "" || tenCaNhan == "" || diaChi == "" || sdt == ""
					|| !tenDangNhap.matches(regexTenDangNhap) || !email.matches(regexMail) || !sdt.matches(regexSDT)
					|| sdt.length() != 10 || bTenDangNhap == true || bEmail == true) {
				if (tenDangNhap == "" || !tenDangNhap.matches(regexTenDangNhap) || bTenDangNhap == true) {
					if (tenDangNhap == "") {
						request.setAttribute("loitendangnhap", "Không được để trống");
					} else if(!tenDangNhap.matches(regexTenDangNhap)) {
						request.setAttribute("loitendangnhap", "Sai cú pháp, kí tự nhập 'a-z,A-Z,0-9'");
					} else {
						request.setAttribute("loitendangnhap", "Tên Đăng Nhập đã tồn tại");
					}
				}
				if (email == "" || !email.matches(regexMail) || bEmail == true) {
					if (email == "") {
						request.setAttribute("loiemail", "Không được để trống");
					} else if(!email.matches(regexMail)) {
						request.setAttribute("loiemail", "Sai cú pháp Email");
					} else {
						request.setAttribute("loiemail", "Email đã được đăng ký");
					}
				}
				if (tenCaNhan == "") {
					request.setAttribute("loitencanhan", "Không được để trống");
				}
				if (diaChi == "") {
					request.setAttribute("loidiachi", "Không được để trống");
				}
				if (sdt == "" || !sdt.matches(regexSDT) || sdt.length() != 10) {
					if (sdt == "") {
						request.setAttribute("loisdt", "Không được để trống");
					} else if (sdt.length() != 10) {
						request.setAttribute("loisdt", "SDT phải đủ 10 con số");
					} else {
						request.setAttribute("loisdt", "Số Điện Thoại phải là SỐ");
					}

				}
				request.setAttribute("tendangnhap", tenDangNhap);
				request.setAttribute("email", email);
				request.setAttribute("tencanhan", tenCaNhan);
				request.setAttribute("diachi", diaChi);
				request.setAttribute("sdt", sdt);
				request.getRequestDispatcher("DangKy.jsp").forward(request, response);
			} else {
				Random rd = new Random();
				BigInteger matKhauRandom = new BigInteger(32, rd);
				String strMatKhauRandom = String.valueOf(matKhauRandom);
				String matKhauMH = AES.encrypt(strMatKhauRandom, scret);
				tkDAO.addTK(tenDangNhap, matKhauMH, tenCaNhan, email, diaChi, sdt, "0");
				
				Email e = new Email();
				e.setGuiTu("thichpfx13302@funix.edu.vn");
				e.setGuiMatKhau("T5dYcX7DMbMV");
				e.setGuiDen(email);
				e.setTieuDeMail("Project_final_thichpfx13302");
				
				StringBuilder sb = new StringBuilder();
				sb.append("Gửi ").append(tenDangNhap).append("<br>");
				sb.append("Mật Khẩu của Bạn là: <b>").append(strMatKhauRandom).append("<b>");
				e.setNoiDung(sb.toString());
				GuiEmail.send(e);
				request.setAttribute("email", email);
				request.setAttribute("tendangnhap", tenDangNhap);
				request.getRequestDispatcher("DangKySuccess.jsp").forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
