<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên Mật Khẩu</title>
<link rel="stylesheet" href="css/dangnhap.css">
</head>

<body>

	<div class="login-box" style="top: 50%">
		<h5 style="color: white;">Vui lòng nhập Thông Tin sau để nhận Mật
			Khẩu của Bạn</h5>
		<form action="QuenMatKhau" method="post">
			<div class="user-box">
				<input type="text" name="tendangnhap" value="${tendangnhap}"> <label>Tên
					Đăng Nhập</label>
			</div>
			<div class="user-box">
				<input type="text" name="email" value="${email}"> <label>Địa
					Chỉ Email</label>
			</div>
			<p style="color: red; font-size: 10px">${loi}</p>

			<div class="login" style="margin-left: 50px">
				<button type="submit" style="margin-top: 20px">
					<span></span> <span></span> <span></span> <span></span> Lấy Mật
					Khẩu
				</button>
			</div>
		</form>
		<div class="footer">
			<a href="home"><button>Hủy</button></a>
		</div>
	</div>
</body>
</html>