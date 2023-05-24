package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import bean.Email;
import bean.TaiKhoan;
import dao.TaiKhoanDAO;
import mahoa.AES;
import mailcommon.GuiEmail;

/**
 * Servlet implementation class ResetMK_TK
 */
@WebServlet("/ResetMK_TK")
public class ResetMK_TK extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetMK_TK() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try {
			HttpSession session = request.getSession();
			Object tkAdminSession = session.getAttribute("tkAdminSession");
			if (tkAdminSession != null) {
				String tenDangNhap = request.getParameter("tendangnhap");
				TaiKhoanDAO tkDAO = new TaiKhoanDAO();
				List<TaiKhoan> listTK = tkDAO.getListUser();
				for (TaiKhoan taiKhoan : listTK) {
					if(tenDangNhap.equals(taiKhoan.getTenDangNhap())) {
						Random rd = new Random();
						BigInteger matKhauRandom = new BigInteger(32, rd);
						String strMatKhauRandom = String.valueOf(matKhauRandom);
						String scret = "Aa@123456";
						String matKhauMH = AES.encrypt(strMatKhauRandom, scret);
						tkDAO.updatePassTK(tenDangNhap, matKhauMH);
						Email e = new Email();
						e.setGuiTu("thichpfx13302@funix.edu.vn");
						e.setGuiMatKhau("T5dYcX7DMbMV");
						e.setGuiDen(taiKhoan.getEmail());
						e.setTieuDeMail("Project_final_thichpfx13302");
						
						StringBuilder sb = new StringBuilder();
						sb.append("Gửi ").append(tenDangNhap).append("<br>");
						sb.append("Mật Khẩu của Bạn là: <b>").append(strMatKhauRandom + "").append("<b>");
						e.setNoiDung(sb.toString());
						GuiEmail.send(e);
						
						request.setAttribute("email", taiKhoan.getEmail());
					}
				}
				
				request.getRequestDispatcher("ResetMKSuccess.jsp").forward(request, response);
				
			}else {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
