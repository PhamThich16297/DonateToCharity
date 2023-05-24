<%@page import="javax.mail.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thay Đổi Mật Khẩu</title>
<link rel="stylesheet" href="css/dangnhap.css">
</head>

<body>
	<%
	Object tkAdminSession = session.getAttribute("tkAdminSession");
	Object tkUserSession = session.getAttribute("tkUserSession");
	if (tkAdminSession != null || tkUserSession != null) {
	%>
	<div class="login-box" style="top: 50%">
		<h5 class="mb-5" style="color: white;">ĐỔI MẬT KHẨU</h5>
		<form action="ThayDoiMatKhau" method="post">
			<div class="user-box">
				<input type="password" name="matkhauhientai"
					value="${matkhauhientai}"> <label>Mật Khẩu Hiện Tại</label>
			</div>
			<div class="user-box">
				<input type="password" name="matkhaumoi" value="${matkhaumoi}">
				<label>Mật Khẩu Mới</label>
			</div>
			<div class="user-box">
				<input type="password" name="matkhaumoi2" value="${matkhaumoi2}">
				<label>Nhập Lại Mật Khẩu Mới</label>
			</div>
			<p style="color: red; font-size: 10px">${loi}</p>
			<div class="login" style="margin-left: 70px">
				<button type="submit">
					<span></span> <span></span> <span></span> <span></span>LƯU THAY ĐỔI
				</button>
			</div>
		</form>
		<div class="footer">
			<%
			if (tkAdminSession != null) {
			%>
			<a href="QuanTri.jsp"><button>Hủy</button></a>
			<%
			} else if (tkUserSession != null) {
			%>
			<a href="home"><button>Hủy</button></a>
			<%
			}
			%>
		</div>
	</div>
	<%
	} else {
	response.sendRedirect("DangNhap.jsp");
	}
	%>
</body>
</html>