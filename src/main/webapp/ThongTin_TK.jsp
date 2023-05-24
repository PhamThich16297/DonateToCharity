<%@page import="dao.QuyenGop1LanDAO"%>
<%@page import="bean.QuyenGop1Lan"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link href="css/admin.css" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Thông Tin Tài Khoản</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0 fixed-top">
		<div class="container d-flex p-2">

			<a class="navbar-brand me-auto" href="home"><img
				src="images/logo.png"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarHome"
				aria-controls="navbarHome" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarHome">
				<div class="d-flex ms-auto">
					<p class="m-auto" style="color: white;">${sessionScope.tkUserSession.tenDangNhap}</p>
					<ul class="navbar-nav m-auto mb-lg-0">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
							role="button" data-bs-toggle="dropdown" aria-expanded="false">
								<i class="bi bi-person-fill"></i>
						</a>
							<ul class="dropdown-menu dropdown-menu-end"
								aria-labelledby="navbarDropdown">
								<li><a class="dropdown-item" href="Sua_ThongTinTK?tendangnhap=${TaiKhoan.tenDangNhap}">Xem thông tin</a></li>
								<li><a class="dropdown-item" href="ThayDoiMatKhau.jsp">Thay
										đổi MK</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item" href="DangXuat">Đăng xuát</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</div>
	</nav>
	<div class="offcanvas offcanvas-start sidebar-nav bg-dark"
		tabindex="-1" id="offcanvasExample">
		<div class="container mt-3">
			<div class="dashboard">
				<a href="#"> <i class="bi bi-speedometer2"></i> <span>Dashboard</span>
				</a>
			</div>

			<hr class="bg-light" />

			<div id="accordion">
				<ul class="dashboard-menu" style="text-align: center;">
					<li><a href="Sua_ThongTinTK?tendangnhap=${TaiKhoan.tenDangNhap}">Thông Tin Tài Khoản</a></li>
					<li><a href="LichSuQuyenGop">Lịch Sử Quyên Góp</a></li>
				</ul>
			</div>
		</div>
	</div>
	<main>
		<div class="main-body">
			<form action="Sua_ThongTinTK" method="post" class="form-dk"
				style="margin: 20px auto 100px auto">
				<div class="row ps-3">
					<label for="tendangnhap" class="col-md-2 p-2">Tên Đăng Nhập</label>
					<div class="col-md-5">
						<input type="text" name="tendangnhap" class="form-control p-2"
							id="tendangnhap" value="${TaiKhoan.tenDangNhap}"
							readonly="readonly"
							style="background: none; border: none; color: fuchsia; font-size: 18px">
					</div>
				</div>

				<div class="row ps-3 p-1">
					<label for="tencanhan" class="col-md-2 p-2">Tên Cá Nhân</label>
					<div class="col-md-5">
						<input type="text" name="tencanhan" class="form-control"
							id="tencanhan" value="${TaiKhoan.tenCaNhan}"
							placeholder="Tên của bạn">
					</div>
					<p class="col-md-4" style="color: red; font-size: 15px">${loitencanhan}</p>
				</div>
				<div class="row ps-3 p-1">
					<label for="email" class="col-md-2 p-2">Địa Chỉ Email</label>
					<div class="col-md-5">
						<input type="text" name="email" class="form-control p-2"
							id="email" value="${TaiKhoan.email}" readonly="readonly"
							style="background: none; border: none; color: fuchsia; font-size: 18px">
					</div>
					<p class="col-md-4" style="color: red; font-size: 15px">${loiemail}</p>
				</div>
				<div class="row ps-3 p-1">
					<label for="diachi" class="col-md-2 p-2">Địa Chỉ Nơi Ở</label>
					<div class="col-md-5">
						<input type="text" name="diachi" class="form-control" id="diachi"
							value="${TaiKhoan.diaChi}" placeholder="Nơi Bạn Sinh Sống">
					</div>
					<p class="col-md-4" style="color: red; font-size: 15px">${loidiachi}</p>
				</div>
				<div class="row ps-3 p-1">
					<label for="sdt" class="col-md-2 p-2">SDT Liên hệ</label>
					<div class="col-md-5">
						<input type="text" name="sdt" class="form-control" id="sdt"
							value="${TaiKhoan.sdt}" placeholder="Số Điện Thoại của bạn">
					</div>
					<p class="col-md-4" style="color: red; font-size: 15px">${loisdt}</p>
				</div>
				<p style="text-align: center; color:red">${luuthanhcong}</p>
				<div class="d-flex justify-content-center m-3">
					<button type="reset" class="btn btn-primary me-3">HỦY</button>
					<button type="submit" class="btn btn-primary">LƯU</button>
				</div>
			</form>
		</div>
	</main>

</body>
</html>