<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/home.css" rel="stylesheet" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Chi Tiết Quyên Góp</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark p-0 fixed-top">
		<div class="container ps-0">

			<a class="navbar-brand me-auto" href="home"><img
				src="images/logo.png"></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
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
							href="DangNhap.jsp">Đăng Nhập</a></li>
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
									<li><a class="dropdown-item" href="#">Xem thông tin</a></li>
									<li><a class="dropdown-item"
										href="ThayDoiMatKhau.jsp">Thay
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



	<div class="container-fuild chitiet-header">
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
					<img src="images/bannertrangchu.png" alt="Image 1" class="d-block"
						style="width: 100%;">
				</div>
				<div class="carousel-item">
					<img src="images/banner2.png" alt="Image 2" class="d-block"
						style="width: 100%;">
				</div>
				<div class="carousel-item">
					<img src="images/banner3.png" alt="Image 3" class="d-block"
						style="width: 100%;">
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

	</div>
	<div class="container">
		<div class="row mt-3">
			<div class="col-md-8">
				<div class="chitiet-body">${dotquyengop.noiDung}</div>
			</div>
			<div class="col-md-4">
				<div class="chitiet-quyengop">
					<p style="font-weight: bold; color: blue">Thông Tin Quyên Góp</p>
					<c:set var="tien" value="${dotquyengop.tienQuyenGop}" />
					<fmt:formatNumber var="VND" type="number" value="${tien}" />
					<p style="font-weight: bold;">Số Tiền Cần Hoàn Thành : ${VND} VND</p>
					<c:set var="tiendaQG" value="${dotquyengop.tienDaQuyenGop}" />
								<fmt:formatNumber var="VNDdaQG" type="number"
									value="${tiendaQG}" />
					<p style="font-weight: bold;">Số Tiền Đã Quyên Góp : ${VNDdaQG} VND</p>
					<div class="row">
						<div class="col-6">
							<p class="mb-0" style="font-weight: bold;">Lượt Quyên Góp</p>
							<p class="ms-5">${dotquyengop.luotQuyenGop}</p>
						</div>
						<div class="col-6">
							<c:if test="${dotquyengop.tgConLai <= 0}" var="tg">
								<p class="mb-0 time p-1" style="color: red;font-weight: bold;">Đã Kết Thúc</p>
							</c:if>	
							<c:if test="${!tg}">
								<p class="mb-0" style="font-weight: bold;">Thời Hạn Còn</p>
								<p class="ms-3">${dotquyengop.tgConLai} Ngày</p>
							</c:if>
						</div>

					</div>
					
					<div class="button-quyengop" style="display: ${dotquyengop.tgConLai <= 0?'none':'block'}">
						<a href="QuyenGop?madot=${dotquyengop.maDot}"><button type="button" style="width: 100%">Quyên Góp</button></a>
					</div>
					<hr class="mb-3 opacity-50">
					<div>
						<p class="mt-2" style="font-weight: bold;">Đồng Hành Cùng Dự
							Án</p>
						<div class="row mb-3">
							<div class="col-3 pe-0">
								<img style="border: 1px solid black; width: 50px; height: 50px; border-radius: 50%"
									src="${dotquyengop.dvtn.urlLogo}">
							</div>
							<div class="col-9 ps-0">${dotquyengop.dvtn.tenDVTN}</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</body>
</html>
