<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
<link href="css/home.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="css/dangnhap.css">
<title>Đăng Ký</title>
</head>
<body>
	<div class="login-box" style="width: 500px">
		<h5 class="mb-5">ĐĂNG KÝ</h5>
		<form class="row" action="DangKy" method="post">

			<div class="user-box">
				<p style="color: red; font-size: 12px" class="mb-0 ms-3 pt-2">${loitendangnhap}</p>
				<input type="text" name="tendangnhap" value="${tendangnhap}">
				<label>Tên Đăng Nhập</label>
			</div>
			<div class="user-box">
				<p style="color: red; font-size: 12px" class="mb-0 ms-3 pt-2">${loiemail}</p>
				<input type="text" name="email" value="${email}"> <label>Email</label>
			</div>
			<div class="user-box">
				<p style="color: red; font-size: 12px" class="mb-0 ms-3 pt-2">${loitencanhan}</p>
				<input type="text" name="tencanhan" value="${tencanhan}">
				<label>Tên Cá Nhân</label>
			</div>

			<div class="user-box">
				<p style="color: red; font-size: 12px" class="mb-0 ms-3 pt-2">${loisdt}</p>
				<input type="text" name="sdt" value="${sdt}"> <label>Số
					Điện Thoại</label>
			</div>
			<div class="user-box">
				<p style="color: red; font-size: 12px" class="mb-0 ms-3 pt-2">${loidiachi}</p>
				<input type="text" name="diachi" value="${diachi}"> <label>Địa
					Chỉ</label>
			</div>

			<div class="login" style="margin-left: 140px;">
				<button class="mt-3" type="submit">
					<span></span> <span></span> <span></span> <span></span>Đăng Ký
				</button>
			</div>
		</form>
		<div class="footer">
			<a href="home"><button>Hủy</button></a>
		</div>
	</div>
</body>
</html>