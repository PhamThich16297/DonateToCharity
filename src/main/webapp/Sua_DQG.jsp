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

<link rel="stylesheet" href="css/admin.css" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="//cdn.ckeditor.com/4.19.0/standard/ckeditor.js"></script>
<title>Sửa Đợt Quyên Góp</title>
</head>
<body>

	<jsp:include page="admin.jsp"></jsp:include>
	<!-- table-main -->
	<main>
		<div class="container-fuild">
			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>SỬA ĐỢT QUYÊN GÓP</h4>
					</div>
				</div>
			</div>

			<form action="Sua_DQG" method="post" enctype="multipart/form-data"
				class="main-body">

				<div class="main-body-col">
					<div class="row mb-3">
						<label for="id" class="col-md-2 p-2">Mã Đợt</label>
						<div class="col-md-10">
							<input type="text" name="id" value="${dotQuyenGop.maDot}"
								readonly="readonly"
								style="border: none; background-color: #e7e9eb" />
						</div>
					</div>
					<div class="row mb-3">
						<label for="tieude" class="col-md-2 p-2">Tiêu Đề</label>
						<div class="col-md-10">
							<input type="text" name="tieude" value="${dotQuyenGop.tieuDe}"
								class="form-control" id="tieude">
							<p style="color: red;">${tieuDe}</p>
						</div>
					</div>

					<div class="row mb-3">
						<label for="imgBanner" class="col-md-2 p-2">IMG Banner</label>
						<div class="d-flex col-md-10">
							<img alt="" src="${dotQuyenGop.urlBanner}"
								style="with: 70px; height: 70px"> <input type="file"
								name="imgBanner" class="form-control m-3" id="imgBanner"
								multiple>

						</div>
					</div>

					<div class="row mb-3">
						<label for="tienquyengop" class="col-md-2 p-2">Tổng Tiền</label>
						<div class="col-md-10">

							<input type="number" name="tienquyengop" value="${tienQG}"
								class="form-control" id="tienquyengop" min="0">

							<p style="color: red;">${tienQuyenGop}</p>
						</div>

					</div>

					<div class="row mb-3">
						<label for="tendvtn" class="col-md-2 p-2">Tên DVTN</label>
						<div class="col-md-10">
							<select class="form-control" name="tendvtn" id="tendvtn">
								<option>Đơn Vị Tiếp Nhận</option>
								<c:forEach items="${listDVTN}" var="dv">
									<option
										<c:if test="${(dotQuyenGop.dvtn.tenDVTN).equals(dv.tenDVTN)}">selected</c:if>>${dv.tenDVTN}</option>
								</c:forEach>
							</select>
							<p style="color: red;">${donviTN}</p>
						</div>
					</div>

					<div class="row mb-3">
						<div class="col-6">
							<label class="me-3" for="ngayBD">Ngày bắt đầu</label> <input
								type="date" name="ngayBD" value="${valngayBD}">
							<p style="color: red;">${thoigianBD}</p>
						</div>
						<div class="col-6">
							<label class="me-3" for="ngayKT">Ngày Kết Thúc</label> <input
								type="date" name="ngayKT" value="${valngayKT}">
							<p style="color: red;">${thoigianKT}</p>
						</div>
						<p style="color: red;">${thoigian}</p>
					</div>

					<div class="mb-3">
						<label class="form-label">Nội Dung</label>
						<p style="color: red;">${noiDung}</p>
						<textarea id="content" name="noidung" class="form-control">${dotQuyenGop.noiDung}</textarea>
					</div>

				</div>

				<div class="d-flex justify-content-end m-3">
					<button type="reset" class="btn btn-primary me-3">HỦY</button>
					<button type="submit" class="btn btn-primary">LƯU</button>
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