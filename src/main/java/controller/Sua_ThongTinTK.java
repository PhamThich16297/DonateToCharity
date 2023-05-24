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

/**
 * Servlet implementation class Sua_ThongTinTK
 */
@WebServlet("/Sua_ThongTinTK")
public class Sua_ThongTinTK extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sua_ThongTinTK() {
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
		request.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			TaiKhoan tkUserSession = (TaiKhoan) session.getAttribute("tkUserSession");
			if (tkUserSession != null) {
				String tenDangNhap = request.getParameter("tendangnhap");
				if (tenDangNhap.equals(tkUserSession.getTenDangNhap())) {
					request.setAttribute("TaiKhoan", tkUserSession);
					request.getRequestDispatcher("ThongTin_TK.jsp").forward(request, response);
				}

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
			TaiKhoan tkUserSession = (TaiKhoan) session.getAttribute("tkUserSession");
			if (tkUserSession != null) {
				String tenDangNhap = request.getParameter("tendangnhap");
				String tenCaNhan = request.getParameter("tencanhan");
				String email = request.getParameter("email");
				String diaChi = request.getParameter("diachi");
				String sdt = request.getParameter("sdt");
				String regexSDT = "[0-9]+";
				
				TaiKhoan tk = new TaiKhoan();
				tk.setTenDangNhap(tenDangNhap);
				tk.setEmail(email);
				tk.setTenCaNhan(tenCaNhan);
				tk.setDiaChi(diaChi);
				tk.setSdt(sdt);
				
				if (tenCaNhan == "" || diaChi == "" || sdt == "" || !sdt.matches(regexSDT) || sdt.length() != 10) {

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

					request.setAttribute("TaiKhoan", tk);
					request.getRequestDispatcher("ThongTin_TK.jsp").forward(request, response);
				} else if (tenDangNhap.equals(tkUserSession.getTenDangNhap())){
					TaiKhoanDAO tkDAO = new TaiKhoanDAO();
					tkDAO.updateTK(tenDangNhap, tenCaNhan, email, diaChi, sdt, "0", "Đang hoạt động");
					request.setAttribute("TaiKhoan", tk);
					request.setAttribute("luuthanhcong", "Lưu Thành Công");
					request.getRequestDispatcher("ThongTin_TK.jsp").forward(request, response);
				}

			} else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
