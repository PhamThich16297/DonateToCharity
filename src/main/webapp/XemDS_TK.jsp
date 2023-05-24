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

<title>Danh Sách Tài Khoản</title>
</head>
<body>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">

			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>XEM DANH SÁCH TÀI KHOẢN</h4>
					</div>
				</div>
			</div>

			<form class="menu-main" action="XemDS_TK" method="post">
				<div class="row text-menu-main">
					<div class="col-1">
						<p style="float: right">Phân Loại</p>
					</div>

					<div class="col-2">
						<select class="form-select" name="phanloai"
							aria-label="Default select example">
							<option value="PLtatca" ${PLtatca}>Tất cả</option>
							<option value="PLadmin" ${PLadmin}>Admin</option>
							<option value="PLuser" ${PLuser}>User</option>
						</select>
					</div>

					<div class="col-1">
						<p style="float: right">Tìm Theo</p>
					</div>

					<div class="col-2">
						<select class="form-select" name="sapxep"
							aria-label="Default select example">
							<option value="SXtheoten" ${SXtheoten}>Tên Đăng Nhập</option>
							<option value="SXtheosdt" ${SXtheosdt}>Số Điện Thoại</option>
						</select>
					</div>

					<div class="col-3">
						<div class="input-group">
							<input type="text" class="form-control" name="search"
								value="${search}" placeholder="Search...">
							<button class="btn btn-primary" type="submit" id="button-addon2">
								<i class="bi bi-search"></i>
							</button>
						</div>
					</div>


				</div>
			</form>


			<div class="table-body">
				<form action="XoaNhieu_TK" method="get">
					<div class="d-flex justify-content-between">

						<div class="form-check" onclick="chonNhieu()">
							<input class="form-check-input" type="checkbox" id="chonall">
							<label class="form-check-label" for="chonall"> Chọn Tất
								Cả </label>
						</div>

						<div class="d-flex">
							<p class="me-3" style="color: red;">${thongbaoxoa}</p>
							<button type="button" class="btn btn-danger checkedbtn disabled"
								data-bs-toggle="modal" data-bs-target="#showmessAll">XÓA
								NHIỀU</button>
							<div class="modal" id="showmessAll">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<h4 class="modal-title">Bạn có chắc chắn xóa</h4>
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
						</div>
					</div>

					<div class="pageindex">
						<c:forEach begin="1" end="${countPageTK}" var="i">
							<a class="${i==pageTK?'active':''}" href="XemDS_TK?pageTK=${i}">${i}</a>
						</c:forEach>
					</div>

					<table class="table table-style">
						<thead>
							<tr>
								<th>Chọn</th>
								<th>Tên Đăng Nhập</th>
								<th>Mật Khẩu</th>
								<th>Tên Cá Nhân</th>
								<th>Email</th>
								<th>Địa Chỉ</th>
								<th>SDT</th>
								<th>Phân Quyền</th>
								<th>Tiền Tài Khoản</th>
								<th>Trạng Thái</th>
								<th>Chức Năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listTK}" var="tk">
								<tr style="text-align: center;">
									<td><input onclick="chonNhieu()" type="checkbox"
										name="chon" value="${tk.tenDangNhap}"></td>
									<td>${tk.tenDangNhap}</td>
									<td>******
										<p id="demo"></p>
									</td>
									<td>${tk.tenCaNhan}</td>
									<td>${tk.email}</td>
									<td>${tk.diaChi}</td>
									<td>${tk.sdt}</td>
									<c:if test="${tk.phanQuyen=='1'}" var="phanQuyen">
										<td>Admin</td>
									</c:if>
									<c:if test="${!phanQuyen}">
										<td>User</td>
									</c:if>

									<td><c:set var="tien" value="${tk.soTienHienCo}" /> <fmt:formatNumber
											var="VND" type="number" value="${tien}" /> ${VND}</td>

									<c:if test="${tk.trangThai=='Đang hoạt động'}" var="trangThai">
										<td style="color: blue;">${tk.trangThai}</td>
									</c:if>
									<c:if test="${!trangThai}">
										<td style="color: red;">${tk.trangThai}</td>
									</c:if>

									<td class="d-flex"><a
										class="btn btn-primary me-2 p-1 ${tk.phanQuyen==1||tk.trangThai=='Không hoạt động'?'disabled':''}"
										style="font-size: 10px;"
										href="ResetMK_TK?tendangnhap=${tk.tenDangNhap}"> Reset<br>Mật
											Khẩu
									</a> <a class="btn btn-primary me-2"
										href="Sua_TK?tendangnhap=${tk.tenDangNhap}">SỬA</a>
										<button onclick="showDelete('${tk.tenDangNhap}')"
											type="button"
											class="btn btn-danger ${tk.phanQuyen==1||tk.trangThai=='Không hoạt động'?'disabled':''}"
											data-bs-toggle="modal" data-bs-target="#showTenDangNhap">XÓA</button></td>
								</tr>




							</c:forEach>
						</tbody>
					</table>

				</form>
			</div>
		</div>
		<div class="modal" id="showTenDangNhap">
			<div class="modal-dialog modal-dialog-centered">
				<div class="modal-content">
					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">Bạn có chắc chắn xóa</h4>
						<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
					</div>
					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-danger"
							data-bs-dismiss="modal">KHÔNG</button>
						<a id="ok" type="button" class="btn btn-danger">CÓ</a>
					</div>
				</div>
			</div>
		</div>

	</main>

	<script>
		function showDelete(str) {
			$('#ok').attr('href', 'Xoa_TK?tendangnhap=' + str);
		}

		function chonNhieu() {
			var checkboxAll = $('#chonall');
			var itemCheckbox = $('input[name="chon"]');
			var checkAllbtn = $('.checkedbtn');
			checkboxAll.change(function() {
				var isCheckedAll = $(this).prop('checked');
				itemCheckbox.prop('checked', isCheckedAll);
				renderCheckAll();
			})
			itemCheckbox
					.change(function() {
						var isCheckAll = itemCheckbox.length === $('input[name="chon"]:checked').length;
						checkboxAll.prop('checked', isCheckAll);
						renderCheckAll();
					})
			function renderCheckAll() {
				var checkCount = $('input[name="chon"]:checked').length;
				if (checkCount > 0) {
					checkAllbtn.removeClass('disabled');
				} else {
					checkAllbtn.addClass('disabled');
				}
			}
		}
	</script>
	<!-- table -->

</body>
</html>