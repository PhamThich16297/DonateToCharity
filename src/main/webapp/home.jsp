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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<title>Trang Chủ</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0 fixed-top">
		<div class="container ps-0">

			<a class="navbar-brand me-auto" href="home"><img
				src="images/logo.png"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarHome"
				aria-controls="navbarHome" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarHome">
				<form class="d-flex w-50 mx-auto">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search..."
							aria-label="Recipient's username"
							aria-describedby="button-addon2">
						<button class="btn btn-primary" type="button" id="button-addon2">
							<i class="bi bi-search"></i>
						</button>
					</div>
				</form>
				<c:if test="${sessionScope.tkUserSession == null}" var="ssUser">
					<ul class="navbar-nav m-2 me-5">
						<li class="nav-item mx-auto"><a class="nav-link me-lg-3"
							href="DangKy.jsp">Đăng Ký</a></li>
						<li class="nav-item mx-auto"><a class="nav-link"
							href="DangNhap">Đăng Nhập</a></li>
					</ul>
				</c:if>
				<c:if test="${!ssUser}">
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
									<li><a class="dropdown-item"
										href="Sua_ThongTinTK?tendangnhap=${sessionScope.tkUserSession.tenDangNhap}">Xem
											thông tin</a></li>
									<li><a class="dropdown-item" href="ThayDoiMatKhau.jsp">Thay
											đổi MK</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item" href="DangXuat">Đăng xuát</a></li>
								</ul></li>
						</ul>
					</div>
				</c:if>


			</div>
		</div>
	</nav>

	<div id="demo" class="carousel slide abc" data-bs-ride="carousel">

		<!-- Indicators/dots -->
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
				class="active"></button>
			<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
			<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
		</div>

		<!-- The slideshow/carousel -->
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/bannertrangchu.png" alt="Los Angeles"
					class="d-block" style="width: 100%; margin-top: 60px;">
			</div>
			<div class="carousel-item">
				<img src="images/banner2.png" alt="Chicago" class="d-block"
					style="width: 100%; margin-top: 60px;">
			</div>
			<div class="carousel-item">
				<img src="images/banner3.png" alt="New York" class="d-block"
					style="width: 100%; margin-top: 60px;">
			</div>
		</div>

		<!-- Left and right controls/icons -->
		<button class="carousel-control-prev" type="button"
			data-bs-target="#demo" data-bs-slide="prev">
			<span class="carousel-control-prev-icon"></span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#demo" data-bs-slide="next">
			<span class="carousel-control-next-icon"></span>
		</button>
	</div>

	<div class="body-home">
		<div class="container">
			<div class="body-home-title">
				<h1>SỰ KIỆN</h1>
			</div>
			<div class="row">
				<c:forEach items="${listDQGHome}" var="dqg">
					<div class="col-lg-4 col-md-6 col-sm-12 mb-3">
						<div class="body-home-content">
							<img src="${dqg.urlBanner}" alt="test">
							<div class="body-home-content-col">
								<a href="ChiTietDotQuyenGop?madot=${dqg.maDot}">${dqg.tieuDe}</a>
								<div class="row px-2">
									<div class="col-2 p-0 my-auto">
										<img
											style="border: 1px solid black; width: 50px; height: 50px"
											src="${dqg.dvtn.urlLogo}" alt="">
									</div>
									<div class="col-7 my-auto">
										<p class="mb-0" style="font-size: 15px;">${dqg.dvtn.tenDVTN}</p>
									</div>
									<div class="col-3 p-0 my-auto">

										<c:if test="${dqg.tgConLai <= 0}" var="tg">
											<p class="mb-0 time p-1" style="color: red">Đã Kết Thúc</p>
										</c:if>
										<c:if test="${!tg}">
											<p class="mb-0 time p-1">Còn ${dqg.tgConLai} ngày</p>
										</c:if>

									</div>
								</div>
								<c:set var="tiendaQG" value="${dqg.tienDaQuyenGop}" />
								<fmt:formatNumber var="VNDdaQG" type="number"
									value="${tiendaQG}" />

								<c:set var="tien" value="${dqg.tienQuyenGop}" />
								<fmt:formatNumber var="VND" type="number" value="${tien}" />
								<p class="my-2 ">Tiến Trình: ${VNDdaQG} / ${VND} VNĐ</p>
								<div class="row">
									<div class="col-6">
										<p class="mb-0" style="text-align: center; font-size: 14px;">Lượt
											Quyên Góp</p>
										<p style="text-align: center; font-weight: bold;">${dqg.luotQuyenGop }</p>
									</div>
									<div class="col-6 my-auto">
										<a href="ChiTietDotQuyenGop?madot=${dqg.maDot}"
											class="mb-0 p-2 quyengop">Quyên Góp</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>