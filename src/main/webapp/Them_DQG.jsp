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
<title>Thêm Đợt Quyên Góp</title>
</head>
<body>

	<jsp:include page="admin.jsp"></jsp:include>
	<!-- table-main -->
	<main>
		<div class="container-fuild">
			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>THÊM ĐỢT QUYÊN GÓP</h4>
					</div>
				</div>
			</div>


			<form action="Them_DQG" method="post" enctype="multipart/form-data" class="main-body">

				<div class="main-body-col">
					<div class="row mb-3">
						<label for="tieude" class="col-md-2 p-2">Tiêu Đề</label>
						<div class="col-md-10">
							<input type="text" name="tieude" class="form-control" id="tieude" value="${valtieuDe}">
							<p style="color: red;">${tieuDe}</p>
						</div>
					</div>

					<div class="row mb-3">
						<label for="imgBanner" class="col-md-2 p-2">IMG Banner</label>
						<div class="col-md-10">
							<input type="file" name="imgBanner" class="form-control" id="imgBanner" multiple>
							<p style="color: red;">${imgBanner}</p>
						</div>
					</div>

					<div class="row mb-3">
						<label for="tienquyengop" class="col-md-2 p-2">Tổng Tiền</label>
						<div class="col-md-10">
							<input type="number" name="tienquyengop" value="${valtienQuyenGop}" class="form-control" id="tienquyengop"
								min="0" step="100000">
							<p style="color: red;">${tienQuyenGop}</p>
						</div>
					</div>

					<div class="row mb-3">
						<label for="tendvtn" class="col-md-2 p-2">Tên DVTN</label>
						<div class="col-md-10">
							<select class="form-control" name="tendvtn" id="tendvtn">
								<option>Đơn Vị Tiếp Nhận</option>
								<c:forEach items="${listDVTN}" var="dvtn">
									<option <c:if test="${valdonviTN.equals(dvtn.tenDVTN)}">selected</c:if>>${dvtn.tenDVTN}</option>
								</c:forEach>
							</select>
							<p style="color: red;">${donviTN}</p>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-6">
							<label class="me-3" for="ngayBD">Ngày bắt đầu</label>
							<input type="date" name="ngayBD" value="${valthoigianBD}">
							<p style="color: red;">${thoigianBD}</p>
						</div>
						<div class="col-6">
							<label class="me-3" for="ngayKT">Ngày Kết Thúc</label>
							<input type="date" name="ngayKT" value="${valthoigianKT}">
							<p style="color: red;">${thoigianKT}</p>
						</div>
						<p style="color: red;">${thoigian}</p>
						
					</div>

					<div class="mb-3">
						<label class="form-label">Nội Dung</label>
						<p style="color: red;">${noiDung}</p>
						<textarea id="content" name="noidung" class="form-control">${valnoiDung}</textarea>
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