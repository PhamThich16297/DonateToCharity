package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.DotQuyenGop;
import bean.TaiKhoan;
import dao.DotQuyenGopDAO;
import dao.QuyenGop1LanDAO;
import dao.TaiKhoanDAO;

/**
 * Servlet implementation class QuyenGop
 */
@WebServlet("/QuyenGop")
public class QuyenGop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuyenGop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkUserSession = session.getAttribute("tkUserSession");
			if (tkUserSession != null) {
				String maDot = request.getParameter("madot");
				DotQuyenGopDAO dqgDAO = new DotQuyenGopDAO();
				DotQuyenGop dqg = dqgDAO.getDQG(Integer.parseInt(maDot));
				
				String tenDangNhap = ((TaiKhoan)tkUserSession).getTenDangNhap();
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listUser = tkDAO.getListUser();
				TaiKhoan taiKhoan = new TaiKhoan();
					for (TaiKhoan tk : listUser) {
						if(tenDangNhap.equals(tk.getTenDangNhap())) {
							taiKhoan = tk;
						}
					}
				session.setAttribute("dqgSession", dqg);
				request.setAttribute("dotquyengop", dqg);
				request.setAttribute("taikhoan", taiKhoan);
				request.getRequestDispatcher("QuyenGop.jsp").forward(request, response);
			} else {
				response.sendRedirect("DangNhap.jsp");
			}
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkUserSession = session.getAttribute("tkUserSession");
			if (tkUserSession != null) {
				String soTien = request.getParameter("tienquyengop") + "";
				String tenDangNhap = ((TaiKhoan)session.getAttribute("tkUserSession")).getTenDangNhap();
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listUser = tkDAO.getListUser();
				TaiKhoan taiKhoan = new TaiKhoan();
					for (TaiKhoan tk : listUser) {
						if(tenDangNhap.equals(tk.getTenDangNhap())) {
							taiKhoan = tk;
						}
					}
				DotQuyenGop dqg = (DotQuyenGop)session.getAttribute("dqgSession");
				if (soTien == "") {
					request.setAttribute("loi", "Cần Nhập số Tiền để Quyên Góp");
					request.setAttribute("taikhoan", taiKhoan);
					request.setAttribute("dotquyengop", dqg);
					request.getRequestDispatcher("QuyenGop.jsp").forward(request, response);
				}
				float soTienFloat = Float.parseFloat(soTien);
				if (soTienFloat <= 0 || soTienFloat > taiKhoan.getSoTienHienCo()) {

					if (soTienFloat <= 0) {
						request.setAttribute("loi", "Nhập Số Tiền Quyên Góp không đúng");
					}

					if (soTienFloat > taiKhoan.getSoTienHienCo()) {
						request.setAttribute("loi", "Số Dư trong Tài Khoản không đủ, Bạn Cần Nạp Thêm");
					}
					request.setAttribute("inputTien", soTien);
					request.setAttribute("taikhoan", taiKhoan);
					request.setAttribute("dotquyengop", dqg);
					request.getRequestDispatcher("QuyenGop.jsp").forward(request, response);
				} else {
					float soTienTaiKhoanFloat = taiKhoan.getSoTienHienCo() - soTienFloat;
					tkDAO.updateSoTienHienCo(taiKhoan.getTenDangNhap(), soTienTaiKhoanFloat);
					QuyenGop1LanDAO qg1lDAO = new QuyenGop1LanDAO();
					qg1lDAO.insertQG1L(dqg.getMaDot(), taiKhoan.getTenDangNhap(), soTien, new Date(System.currentTimeMillis()));
					
					request.setAttribute("tendotquyengop", dqg.getTieuDe());
					request.setAttribute("sotienquyengop", soTien);
					request.getRequestDispatcher("QuyenGopSuccess.jsp").forward(request, response);
				}
			}else {
				response.sendRedirect("DangNhap.jsp");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
