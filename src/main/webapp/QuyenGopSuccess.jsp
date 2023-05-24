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
<link rel="stylesheet" href="css/dangnhap.css">
<title>Quyên Góp Thành Công</title>
</head>
<body>

	<div class="modal-dialog modal-lg">
		<div class="modal-content" style="background: rgba(0, 0, 0, .5);">

			<!-- Modal Header -->
			<div class="modal-header" style="border: none">
				<h4 class="modal-title" style="margin: 0 auto; color: #03e9f4;">CẢM ƠN BẠN ĐÃ ỦNG HỘ</h4>
				
			</div>
			<div class="row m-3" style="color:white; font-size: 18px">
				<p class="col-4">Đợt Quyên Góp:</p>
				<p class="col-8">${tendotquyengop}</p>
			</div>
			<div class="row m-3" style="color:white;font-size: 18px">
				<p class="col-4">Số Tiền Quyên Góp: </p>
				<c:set var="tien" value="${sotienquyengop}" />
				<fmt:formatNumber var="VND" type="number" value="${tien}" />
				<p class="col-8">${VND} VNĐ</p>
			</div>
			<!-- Modal footer -->
			<div class="modal-footer" style="border: none">
				<div class="footer">
					<a href="home"><button>Trang Chủ</button></a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>