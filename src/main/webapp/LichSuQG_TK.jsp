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
								<li><a class="dropdown-item"
									href="Sua_ThongTinTK?tendangnhap=${TaiKhoan.tenDangNhap}">Xem
										thông tin</a></li>
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
					<li><a
						href="Sua_ThongTinTK?tendangnhap=${TaiKhoan.tenDangNhap}">Thông
							Tin Tài Khoản</a></li>
					<li><a href="LichSuQuyenGop">Lịch Sử Quyên Góp</a></li>
				</ul>
			</div>
		</div>
	</div>
	<main>
		<div class="main-header">
			<div class="navbar navbar-expand-sm">
				<div class="container">
					<h4>LỊCH SỬ QUYÊN GÓP</h4>
				</div>
			</div>
		</div>
		<div class="main-body">
			<div class="table-body">
				<table class="table table-style">
					<thead>
						<tr>
							<th>STT</th>
							<th>Tên Đợt Quyên Góp</th>
							<th>Số Tiền Quyên Góp</th>
							<th>Thời Gian Quyên Góp</th>
						</tr>
					</thead>
					<tbody>
						<%
						int count = 1;
						%>
						<c:forEach items="${listQG1L}" var="qg1l">
							<tr>
								<td><%=count++%></td>
								<td>${qg1l.dqg.tieuDe}</td>
								<c:set var="tienQG" value="${qg1l.soTien}" /> <fmt:formatNumber
											var="quyengop" type="number" value="${tienQG}" />
								<td>${quyengop}</td>
								<td>${qg1l.thoiGianQG}</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</main>

</body>
</html>