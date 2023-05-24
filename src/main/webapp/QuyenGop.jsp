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
<title>Quyên Góp</title>
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

	<div class="body-home">
		<div class="container">
			<div class="row donate">
				<div class="col-md-7">
					<div class="body-home-content" style="margin-top: 15px;">
						<img src="${dotquyengop.urlBanner}" alt="test">
						<div class="body-home-content-col">
							<a href="#">${dotquyengop.tieuDe}</a>
							<div class="row px-2">
								<div class="col-2 p-0 my-auto">
									<img class="ms-5"
										style="border: 1px solid black; width: 50px; height: 50px"
										src="${dotquyengop.dvtn.urlLogo}">
								</div>
								<div class="col-7 my-auto">
									<p class="mb-0" style="font-size: 15px;">${dotquyengop.dvtn.tenDVTN}</p>
								</div>
								<div class="col-3 p-0 my-auto pe-5">
									<p class="mb-0 time" style="color: red; font-weight: bold;">Còn
										${dotquyengop.tgConLai} ngày</p>
								</div>
							</div>

							<div class="row">
								<div class="col-6">
									<c:set var="tien" value="${dotquyengop.tienQuyenGop}" />
									<fmt:formatNumber var="VND" type="number" value="${tien}" />
									
									<c:set var="tiendaQG" value="${dotquyengop.tienDaQuyenGop}" />
									<fmt:formatNumber var="VNDdaQG" type="number"
									value="${tiendaQG}" />
									<p class="my-3 ps-5">
										<b style="color: blue">Tiến Trình:</b> ${VNDdaQG} / ${VND} VNĐ
									</p>
								</div>
								<div class="col-6">
									<p class="my-3 ps-5">
										<b style="color: blue">Lượt Quyên Góp:</b> ${dotquyengop.luotQuyenGop}
									</p>

								</div>

							</div>
						</div>
					</div>
				</div>
				<div class="col-md-5">
					<div class="body-home-content" style="margin-top: 15px">
						<div class="body-home-content-col p-5">
							<form action="QuyenGop" method="post">
								<div class="row">
									<p class="col-4"
										style="color: blue; font-weight: bold; font-size: 20px">Tài
										Khoản:</p>
									<p class="col-8" style="font-weight: bold; font-size: 20px">${taikhoan.tenDangNhap}</p>
								</div>
								<div class="row">
									<p class="col-4"
										style="color: blue; font-weight: bold; font-size: 20px">Số
										Dư:</p>
									<c:set var="sodu" value="${taikhoan.soTienHienCo}" />
									<fmt:formatNumber var="soduVND" type="number" value="${sodu}" />
									<p class="col-8" style="font-weight: bold; font-size: 20px">${soduVND}
										VNĐ</p>
									
								</div>
								<label for="tienquyengop" class="mb-3"
									style="color: blue; font-weight: bold; font-size: 20px">Nhập
									số Tiền Quyên Góp</label>
								<div class="input-group mb-3">
									<input type="number" name="tienquyengop" value="${inputTien}"
										class="form-control" id="tienquyengop" min="0" step="10000">
									<span class="input-group-text">VNĐ</span>
								</div>
								<p style="color: red;">${loi}</p>

								<button type="button" class="mb-0 p-2 quyengop"
									style="width: 100%; font-size: 25px" data-bs-toggle="modal"
									data-bs-target="#showmessQuyenGop">Quyên Góp</button>

								<div class="modal" id="showmessQuyenGop">
									<div class="modal-dialog modal-dialog-centered">

										<div class="modal-content">

											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title">Bạn có chắc chắn Quyên Góp</h4>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal"></button>
											</div>

											<!-- Modal footer -->
											<div class="modal-footer">
												<button type="button" class="btn btn-danger"
													data-bs-dismiss="modal">KHÔNG</button>
												<button type="submit" class="btn btn-danger">CÓ</button>
											</div>
										</div>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>