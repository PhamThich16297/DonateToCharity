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
<title>Thống kê Người Dùng</title>
</head>
<body>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">

			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>THỐNG KÊ NGƯỜI DÙNG</h4>
					</div>
				</div>
			</div>


			<div class="table-body">
				<div class="row">
					<form action="ThongKe_TK" method="post" class="col-5 row"
						style="font-size: 14px; font-weight: bold;">
						<div class="col-4">
							<label for="tt" class="p-1" style="float: right;">Trạng
								Thái</label>
						</div>
						<div class="col-6">
							<select class="form-control ps-2 p-1" name="trangthai" id="tt">
								<option value="TTtatca" ${TTtatca}>Tất Cả</option>
								<option value="TThoatdong" ${TThoatdong}>Đang Hoạt Động</option>
								<option value="TTkhonghoatdong" ${TTkhonghoatdong}>Không Hoạt Động</option>
							</select>
						</div>

						<div class="col-4">
							<label for="tgqg" class="p-1" style="float: right;">Tham Gia QG</label>
						</div>
						<div class="col-6">
							<select class="form-control ps-2 p-1" name="thamgiaQG"
								id="tgqg">
								<option value="TGtatca" ${tatca}>Tất Cả</option>
								<option value="TGco" ${dathamgia}>Đã Tham Gia</option>
								<option value="TGkhong" ${chuathamgia}>Chưa Tham Gia</option>
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

							<div class="me-3 p-2 bg-info" style="width: 400px">
								<div class="mb-2"
									style="border: 1px solid red; border-radius: 10px">
									<p class="m-1">Tài Khoản Đăng Ký</p>
									<p class="m-1">${countAll_TK}</p>
								</div>

								<div class="d-flex justify-content-center bg-secondary mb-3">
									<div class="m-2 p-2 bg-info">
										<p class="m-1">Đang Hoạt Động</p>
										<p class="m-1">${countDangHD_TK}</p>
									</div>
									<div class="m-2 p-2 bg-info">
										<p class="m-1">Không Hoạt Động</p>
										<p class="m-1">${countKhongHD_TK}</p>
									</div>
								</div>
							</div>

						</div>
					</div>

				</div>

				<div class="pageindex">
					<c:forEach begin="1" end="${countPage}" var="i">
						<a class="${i==page?'active':''}" href="ThongKe_TK?page=${i}">${i}</a>
					</c:forEach>
				</div>
				<table class="table table-style">
					<thead>
						<tr class="row">

							<th class="col-2">Tên Tài Khoản</th>
							<th class="col-3">Trạng Thái</th>
							<th class="col-5">Đợt Quyên Góp</th>
							<th class="col-2">Số Tiền Đã Quyên Góp</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach items="${listUser}" var="qg1l">

							<tr class="row" style="text-align: center;">
								<td class="col-2">${qg1l.tk.tenDangNhap}</td>
								<td class="col-2">${qg1l.tk.trangThai}</td>

								<td class="col-6"><c:if test="${qg1l.dqg == null}"
										var="tieude">
									Không Có Dữ Liệu
									</c:if> <c:if test="${!tieude}">
									${qg1l.dqg.tieuDe}
									</c:if></td>
								<c:set var="tien" value="${qg1l.soTien}"/>
								<fmt:formatNumber var="VND" type="number" value="${tien}"/>
								<td class="col-2">
									<c:if test="${qg1l.soTien == 0}" var="tien">
									0
									</c:if> <c:if test="${!tien}">
									${VND} VND
									</c:if></td>


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
