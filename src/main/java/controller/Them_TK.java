package controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class Them_TK
 */
@WebServlet("/Them_TK")
public class Them_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Them_TK() {
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
				request.getRequestDispatcher("Them_TK.jsp").forward(request, response);
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
		request.setCharacterEncoding("utf-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String regexTenDangNhap = "[a-zA-Z0-9]+";
				String regexMatKhau = "[a-zA-Z0-9_!@#$%^&*]+";
				String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
				String regexSDT = "[0-9]+";
				String scret = "Aa@123456";

				String tenDangNhap = request.getParameter("tendangnhap");
				String matKhau = request.getParameter("matkhau");
				String matKhauMH = AES.encrypt(matKhau, scret);
				String matKhau2 = request.getParameter("matkhau2");
				String tenCaNhan = request.getParameter("tencanhan");
				String email = request.getParameter("email");
				String diaChi = request.getParameter("diachi");
				String sdt = request.getParameter("sdt");
				String phanQuyen = request.getParameter("phanquyen");

				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListAllTK();

				String suDungTenDangNhap = "";
				String suDungEmail = "";
				for (TaiKhoan tk : listTK) {
					if (tenDangNhap.equals(tk.getTenDangNhap())) {
						suDungTenDangNhap = "checktendangnhap";
					}
					if (email.equals(tk.getEmail())) {
						suDungEmail = "checkemail";
					}
				}

				if (tenDangNhap == "" || matKhau == "" || matKhau2 == "" || tenCaNhan == "" || email == ""
						|| diaChi == "" || sdt == "" || !tenDangNhap.matches(regexTenDangNhap)
						|| !matKhau.matches(regexMatKhau) || matKhau.length() < 8 || phanQuyen == null
						|| !sdt.matches(regexSDT) || sdt.length() != 10 || suDungTenDangNhap.equals("checktendangnhap")
						|| suDungEmail.equals("checkemail")) {

					if (tenDangNhap == "" || !tenDangNhap.matches(regexTenDangNhap)) {
						if (tenDangNhap == "") {
							request.setAttribute("loitendangnhap", "Không được để trống");
						} else {
							request.setAttribute("loitendangnhap", "Sai cú pháp, kí tự nhập 'a-z,A-Z,0-9'");
						}
					}
					if (suDungTenDangNhap.equals("checktendangnhap")) {
						request.setAttribute("loitendangnhap", "Tên Đăng Nhập đã được sử dụng");
					}
					if (matKhau == "" || (!matKhau.matches(regexMatKhau) || matKhau.length() < 8)) {
						if (matKhau == "") {
							request.setAttribute("loimatkhau", "Không được để trống");
						} else {
							if (!matKhau.matches(regexMatKhau)) {
								request.setAttribute("loimatkhau", "Sai cú pháp, kí tự nhập a-z,A-Z,0-9,_!@#$%^&*");
							} else {
								request.setAttribute("loimatkhau", "Mật Khẩu có ít nhất 8 kí tự");
							}
						}
					}
					if (matKhau2 == "" || !matKhau.equals(matKhau2)) {
						if (matKhau2 == "") {
							request.setAttribute("loiMKkhongkhop", "Không được để trống");
						} else {
							request.setAttribute("loiMKkhongkhop", "Mật khẩu không trùng khớp");
						}
					}
					if (email == "" || !email.matches(regexMail)) {
						if (email == "") {
							request.setAttribute("loiemail", "Không được để trống");
						} else {
							request.setAttribute("loiemail", "Sai cú pháp Email");
						}
					}
					if (suDungEmail.equals("checkemail")) {
						request.setAttribute("loiemail", "Email đã được đăng ký");
					}
					if (tenCaNhan == "") {
						request.setAttribute("loitencanhan", "Không được để trống");
					}
					if (sdt == "" || (!sdt.matches(regexSDT) || sdt.length() != 10)) {
						if (sdt == "") {
							request.setAttribute("loisdt", "Không được để trống");
						} else {
							if (!sdt.matches(regexSDT)) {
								request.setAttribute("loisdt", "SDT phải là số");
							} else {
								request.setAttribute("loisdt", "SDT phải đủ 10 con số");
							}
						}
					}
					if (diaChi == "") {
						request.setAttribute("loidiachi", "Không được để trống");
					}
					if (phanQuyen == null) {
						request.setAttribute("loiphanquyen", "Chưa chọn quyền truy cập");
					}

					request.setAttribute("valtendangnhap", tenDangNhap);
					request.setAttribute("valmatkhau", matKhau);
					request.setAttribute("valmatkhau2", matKhau2);
					request.setAttribute("valemail", email);
					request.setAttribute("valtencanhan", tenCaNhan);
					request.setAttribute("valdiachi", diaChi);
					request.setAttribute("valsdt", sdt);
					request.setAttribute("valphanquyen", phanQuyen);

					request.getRequestDispatcher("Them_TK.jsp").forward(request, response);
				} else {
					String pq = "";

					if (phanQuyen.equals("admin")) {
						pq = "1";
					} else if (phanQuyen.equals("user")) {
						pq = "0";
					}
					tkDAO.addTK(tenDangNhap, matKhauMH, tenCaNhan, email, diaChi, sdt, pq);
					response.sendRedirect("XemDS_TK");
				}
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
