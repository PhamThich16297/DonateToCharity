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

/**
 * Servlet implementation class Sua_TK
 */
@WebServlet("/Sua_TK")
public class Sua_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sua_TK() {
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
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String tenDangNhap = request.getParameter("tendangnhap");
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListAllTK();
				TaiKhoan taiKhoan = new TaiKhoan();
				for (TaiKhoan tk : listTK) {
					if (tenDangNhap.equals(tk.getTenDangNhap())) {
						taiKhoan = tk;
					}
				}
				request.setAttribute("TaiKhoan", taiKhoan);
				request.getRequestDispatcher("Sua_TK.jsp").forward(request, response);
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
				String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
				String regexSDT = "[0-9]+";

				String tenDangNhap = request.getParameter("tendangnhap");
				String tenCaNhan = request.getParameter("tencanhan");
				String email = request.getParameter("email");
				String diaChi = request.getParameter("diachi");
				String sdt = request.getParameter("sdt");
				String phanQuyen = request.getParameter("phanquyen");
				String trangThai = request.getParameter("trangthai");

				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListAllTK();

				String suDungEmail = "";
				for (TaiKhoan tk : listTK) {
					if (email.equals(tk.getEmail()) && tenDangNhap.equals(tk.getTenDangNhap())) {
						suDungEmail = email;
					} else if (email.equals(tk.getEmail()) && !tenDangNhap.equals(tk.getTenDangNhap())) {
						suDungEmail = "checkemail";
					}
				}

				if (tenDangNhap == "" || tenCaNhan == "" || email == "" || diaChi == "" || sdt == ""
						|| !sdt.matches(regexSDT) || sdt.length() != 10 || suDungEmail.equals("checkemail")) {

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

					TaiKhoan tk = new TaiKhoan();
					tk.setTenDangNhap(tenDangNhap);
					tk.setEmail(email);
					tk.setTenCaNhan(tenCaNhan);
					tk.setDiaChi(diaChi);
					tk.setSdt(sdt);
					String pq = "";
					if (phanQuyen.equals("admin")) {
						pq = "1";
					} else if (phanQuyen.equals("user")) {
						pq = "0";
					}
					tk.setPhanQuyen(pq);
					String tt = "";
					if (trangThai.equals("on")) {
						tt = "Đang hoạt động";
					} else if (trangThai.equals("off")) {
						tt = "Không hoạt động";
					}
					tk.setTrangThai(tt);

					request.setAttribute("TaiKhoan", tk);
					request.getRequestDispatcher("Sua_TK.jsp").forward(request, response);
				} else {
					String pq = "";
					String tt = "";
					if (phanQuyen.equals("admin")) {
						pq = "1";
					} else if (phanQuyen.equals("user")) {
						pq = "0";
					}
					if (trangThai.equals("on")) {
						tt = "Đang hoạt động";
					} else if (trangThai.equals("off")) {
						tt = "Không hoạt động";
					}
					tkDAO.updateTK(tenDangNhap, tenCaNhan, email, diaChi, sdt, pq, tt);
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
