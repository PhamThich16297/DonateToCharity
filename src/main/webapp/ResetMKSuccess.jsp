<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Reset Mật Khẩu Thành Công</title>
</head>
<body>

	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content" style="background: rgba(0, 0, 0, .5);">

			<!-- Modal Header -->
			<div class="modal-header" style="border: none">
				<h4 class="modal-title" style="margin: 0 auto; color: #03e9f4;text-align: center">Reset Mật Khẩu Thành Công<br> Mật Khẩu đã gửi vào Email: <br></h4>
				
			</div>
			<div class="modal-body" style="border: none">
				<h5 class="modal-title" style="margin: 0 auto; color: white;text-align: center">${email}</h5>
				
			</div>

			<!-- Modal footer -->
			<div class="modal-footer" style="border: none">
				<div class="footer">
					<a href="XemDS_TK"><button>Quay về</button></a>
				</div>
			</div>
		</div>
	</div>

</body>
</html>