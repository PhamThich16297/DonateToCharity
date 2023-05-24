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
<title>Thống kê Đợt Quyên Góp</title>
</head>
<body>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">

			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>THỐNG KÊ ĐỢT QUYÊN GÓP</h4>
					</div>
				</div>
			</div>


			<div class="table-body">
				<div class="row">
					<form action="ThongKe_DQG" method="post" class="col-5 row"
						style="font-size: 14px; font-weight: bold;">
						<div class="col-4">
							<label for="tt" class="p-1" style="float: right;">Trạng
								Thái</label>
						</div>
						<div class="col-6">
							<select class="form-control ps-2 p-1" name="trangthai" id="tt">
								<option value="tatca" ${TTtatca}>Tất Cả</option>
								<option value="dangdienra" ${TTdangdienra}>Đang Diễn Ra</option>
								<option value="daketthuc" ${TTdaketthuc}>Đã Kết Thúc</option>
							</select>
						</div>

						<div class="col-4">
							<label for="lqg" class="p-1" style="float: right;">Lượt
								Quyên Góp</label>
						</div>
						<div class="col-6">
							<select class="form-control ps-2 p-1" name="luotquyengop"
								id="lqg">
								<option value="tang" ${luottang}>Tăng</option>
								<option value="giam" ${luotgiam}>Giảm</option>
							</select>
						</div>

						<div class="col-4 container">
							<button style="float: right;" type="submit"
								class="btn btn-success">LỌC</button>
						</div>

					</form>
					<div class="col-7">
						<div class="d-flex justify-content-center"
							style="text-align: center; font-weight: bold">

							<div class="me-3 p-2 bg-info" style="width: 300px">
								<div class="mb-2"
									style="border: 1px solid red; border-radius: 10px">
									<p class="m-1">Số Đợt Quyên Góp</p>
									<p class="m-1">${countAll_DQG}</p>
								</div>

								<div class="d-flex justify-content-center bg-secondary mb-3">
									<div class="m-2 p-2 bg-info">
										<p class="m-1">Đang Diễn Ra</p>
										<p class="m-1">${countDDR_DQG}</p>
									</div>
									<div class="m-2 p-2 bg-info">
										<p class="m-1">Đã Kết Thúc</p>
										<p class="m-1">${countKT_DQG}</p>
									</div>
								</div>
							</div>
							<div class="ms-3 p-2 bg-info" style="width: 300px">
								<div class="mb-2 mt-2"
									style="border: 1px solid red; border-radius: 10px">
									<p class="m-2">Tổng Lượt Quyên Góp</p>
									<p class="m-2">${countLuotAll}</p>
								</div>

								<div class="mb-2"
									style="border: 1px solid red; border-radius: 10px">
									<p class="m-2">Tổng Tiền Đã Quyên Góp</p>
									<c:set var="tongtien" value="${countTienAll}" />
									<fmt:formatNumber var="tong" type="number" value="${tongtien}" />
									<p class="m-2">${tong}</p>
								</div>
							</div>

						</div>
					</div>

				</div>

				<div class="pageindex">
					<c:forEach begin="1" end="${countPage}" var="i">
						<a class="${i==page?'active':''}" href="ThongKe_DQG?page=${i}">${i}</a>
					</c:forEach>
				</div>
				<table class="table table-style">
					<thead>
						<tr>
							<th>#</th>
							<th>Tên Đợt Quyên Góp</th>
							<th>Trạng Thái</th>
							<th>Đơn Vị Tiếp Nhận</th>
							<th>Số Tiền Hoàn Thành</th>
							<th>Số Tiền Đã Quyên Góp</th>
							<th>Lượt</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listDQG}" var="dqg">
							<tr style="text-align: center;">
								<td>${dqg.maDot}</td>
								<td>${dqg.tieuDe}</td>

								<c:if test="${dqg.tgConLai <= 0}" var="tgConLai">
									<td style="color: red; font-size: 14px; font-weight: bold;">Đã
										Kết Thúc</td>
								</c:if>
								<c:if test="${!tgConLai}">
									<td>${dqg.tgConLai}Ngày</td>
								</c:if>

								<td>${dqg.dvtn.tenDVTN}</td>
								<c:set var="tienQG" value="${dqg.tienQuyenGop}" />
								<fmt:formatNumber var="QG" type="number" value="${tienQG}" />
								<td>${QG}</td>
								<c:set var="tiendaQG" value="${dqg.tienDaQuyenGop}" />
								<fmt:formatNumber var="daQG" type="number" value="${tiendaQG}" />
								<td>${daQG}</td>
								<td>${dqg.luotQuyenGop}</td>

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
