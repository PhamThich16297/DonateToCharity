<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">

<link rel="stylesheet" href="css/admin.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="//cdn.ckeditor.com/4.19.0/standard/ckeditor.js"></script>
<title>Sửa Tài Khoản</title>
</head>
<body>

	<jsp:include page="admin.jsp"></jsp:include>
	<!-- table-main -->
	<main>
		<div class="container-fuild">
			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>SỬA TÀI KHOẢN</h4>
					</div>
				</div>
			</div>

			<div class="main-body">
				<form action="Sua_TK" method="post" class="form-dk"
					style="margin: 20px auto 100px auto">
					<div class="row ps-3">
						<label for="tendangnhap" class="col-md-2 p-2">Tên Đăng
							Nhập</label>
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
							<input type="text" name="email" class="form-control" id="email"
								value="${TaiKhoan.email}" placeholder="Nhập Email">
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
					<div class="row ps-3 p-1">
						<label class="col-md-2 ps-2">Phân Quyền</label>
						<div class="col-md-5 row">
							<div class="col-6">
								<input type="radio" class="form-check-input" id="admin"
									name="phanquyen" value="admin"
									${TaiKhoan.phanQuyen=="1"?"checked":""}> <label
									class="form-check-label" for="admin">Admin</label>
							</div>
							<c:if test="${TaiKhoan.phanQuyen==0}">
								<div class="col-6">
									<input type="radio" class="form-check-input" id="user"
										name="phanquyen" value="user"
										${TaiKhoan.phanQuyen=="0"?"checked":""}> <label
										class="form-check-label" for="user">User</label>
								</div>
							</c:if>
						</div>
					</div>
					<div class="row ps-3 p-1">
						<label class="col-md-2 ps-2">Trạng Thái</label>
						<div class="col-md-5 row">
							<div class="col-6">
								<input type="radio" class="form-check-input" id="on"
									name="trangthai" value="on"
									${TaiKhoan.trangThai=="Đang hoạt động"?"checked":""}> <label
									class="form-check-label" for="on">Bật</label>
							</div>
							<div class="col-6">
								<input type="radio" class="form-check-input" id="off"
									name="trangthai" value="off"
									${TaiKhoan.trangThai=="Không hoạt động"?"checked":""}>
								<label class="form-check-label" for="off">Tắt</label>
							</div>
						</div>
					</div>
					<div class="d-flex justify-content-center m-3">
						<button type="reset" class="btn btn-primary me-3">HỦY</button>
						<button type="submit" class="btn btn-primary">LƯU</button>
					</div>
				</form>
			</div>
		</div>

	</main>


	<!-- table -->
</body>
</html>