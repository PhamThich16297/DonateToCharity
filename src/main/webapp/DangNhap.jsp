<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Nhập</title>
<link rel="stylesheet" href="css/dangnhap.css">
</head>

<body>

	<div class="login-box" style="top:50%">
		<img src="images/man.png">
		<form action="DangNhap" method="post">
			<div class="user-box">
				<input type="text" name="tendangnhap" value="${tendangnhap}">
				<label>Tên Đăng Nhập</label>
			</div>
			<div class="user-box">
				<input type="password" name="matkhau" value="${matkhau}">
				<label>Mật Khẩu</label>
			</div>
			<p style="color: red; font-size: 10px">${loidangnhap}</p>
			<div class="user-checkbox">
				<input type="checkbox" name="nhomatkhau">
				<label>Nhớ Mật Khẩu</label><br>
			</div>
			<div class="login">
				<button type="submit">
					<span></span> <span></span> <span></span> <span></span> ĐĂNG NHẬP
				</button>
			</div>
		</form>
		<div class="footer">
			<a href="home"><button>Hủy</button></a>
			<div class="right">
				<a href="QuenMatKhau.jsp">Quên Mật Khẩu?</a>
			</div>

		</div>
	</div>
</body>
</html>