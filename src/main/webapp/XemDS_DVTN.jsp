<%@page import="bean.TaiKhoan"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
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
<link href="css/admin.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<title>Danh Sách Đơn Vị Tiếp Nhận</title>
</head>
<body>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">

			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>XEM DANH SÁCH ĐƠN VỊ TIẾP NHẬN</h4>
					</div>
				</div>
			</div>

			<form class="menu-main d-flex" action="XemDS_DVTN" method="post">
				<div class="text-menu-main">
					<div class="input-group">
						<input style="width: 300px" type="text" class="form-control"
							name="search" value="${search}" placeholder="Search...">
						<button class="btn btn-primary" type="submit" id="button-addon2">
							<i class="bi bi-search"></i>
						</button>
					</div>
				</div>
				<p class="ms-2" style="color:red; font-size: 14px">${ketqua}</p>
			</form>
			


			<div class="table-body">

				<div class="pageindex">
					<c:forEach begin="1" end="${countPageDVTN}" var="i">
						<a class="${i==pageDVTN?'active':''}"
							href="XemDS_DVTN?pageDVTN=${i}">${i}</a>
					</c:forEach>
				</div>

				<table class="table table-style">
					<thead>
						<tr class="row">
							<th class="col-1">#</th>
							<th class="col-3">Tên DVTN</th>
							<th class="col-2">Logo</th>
							<th class="col-5">Thông Tin</th>
							<th class="col-1">Chức Năng</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listDVTN}" var="dvtn">
							<tr style="text-align: center;" class="row">
								<td class="col-1">${dvtn.maDVTN}</td>
								<td class="col-3">${dvtn.tenDVTN}</td>
								<td class="col-2"><img style="width: 100px; height: 100px"
									src="${dvtn.urlLogo}"></td>
								<td class="col-5"><p
										style="text-align-last: center; text-align: justify;">${dvtn.thongTin}</p></td>
								<td class="col-1"><a class="btn btn-primary"
									href="Sua_DVTN?madvtn=${dvtn.maDVTN}">SỬA</a></td>
							</tr>

						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

	</main>

	<!-- table -->

</body>
</html>
</html>