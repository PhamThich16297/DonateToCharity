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
<title>Thêm Đơn Vị Tiếp Nhận</title>
</head>
<body>

	<jsp:include page="admin.jsp"></jsp:include>
	<!-- table-main -->
	<main>
		<div class="container-fuild">
			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>THÊM ĐƠN VỊ TIẾP NHẬN</h4>
					</div>
				</div>
			</div>


			<form action="Them_DVTN" method="post" enctype="multipart/form-data"
				class="main-body">

				<div class="main-body-col">
					<div class="row mb-3">
						<label for="tendvtn" class="col-md-2 p-2 mt-0">Tên Đơn Vị
							Tiếp Nhận</label>
						<div class="col-md-10">
							<input type="text" name="tendvtn" class="form-control"
								id="tendvtn" value="${valtendvtn}">
							<p style="color: red;">${loitendvtn}</p>
						</div>
					</div>

					<div class="row mb-3">
						<label for="imgLogo" class="col-md-2 p-2 mt-0">Hình Ảnh
							Logo</label>
						<div class="col-md-10">
							<input type="file" name="imgLogo" class="form-control"
								id="imgLogo" multiple>
							<p style="color: red;">${loiimglogo}</p>
						</div>
					</div>


					<div class="mb-3">
						<label class="form-label mt-0">Thông Tin</label>
						<p style="color: red;">${loithongtin}</p>
						<textarea id="content" name="thongtin" class="form-control">${valthongtin}</textarea>
					</div>

				</div>

				<div class="d-flex justify-content-end m-3">
					<button type="reset" class="btn btn-primary me-3">HỦY</button>
					<button type="submit" class="btn btn-primary">Thêm</button>
				</div>
			</form>

		</div>

	</main>

	<script>
		var editor = "";
		$(document).ready(function() {
			editor = CKEDITOR.replace('content');
		});
	</script>
	<!-- table -->
</body>
</html>