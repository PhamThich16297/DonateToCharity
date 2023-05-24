<%@page import="bean.DotQuyenGop"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
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

<title>Danh Sách Đợt Quyên Góp</title>
</head>
<body>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">
		
			<div class="main-header">
				<div class="navbar navbar-expand-sm">
					<div class="container">
						<h4>XEM DANH SÁCH ĐỢT QUYÊN GÓP</h4>
					</div>
				</div>
			</div>

			<form class="menu-main" action="XemDS_DQG" method="post">
			
				<div class="row text-menu-main">
					<div class="col-1">
						<p style="float: right">Phân Loại</p>
					</div>

					<div class="col-2">
						<select class="form-select" name="phanloai"
							aria-label="Default select example">
							<option value="PLtatca" ${PLtatca}>Tất cả</option>
							<option value="PLdangdienra" ${PLdangdienra}>Đang diễn
								ra</option>
							<option value="PLdaketthuc" ${PLdaketthuc}>Đã kết Thúc</option>
						</select>
					</div>
					
					<div class="col-1">
						<p style="float: right">Sắp xếp</p>
					</div>
					
					<div class="col-2">
						<select class="form-select" name="sapxep"
							aria-label="Default select example">
							<option value="SXtheoID" ${SXtheoID}>Theo ID</option>
							<option value="SXtheotien" ${SXtheotien}>Theo Số Tiền
								Cần Huy Động</option>
						</select>
					</div>
					
					<div class="col-2">
						<select class="form-select" name="tanggiam"
							aria-label="Default select example">
							<option value="tang" ${tang}>Tăng Dần</option>
							<option value="giam" ${giam}>Giảm dần</option>
						</select>
					</div>
					
					<div class="col-2">
						<button type="submit" class="btn btn-primary">THỰC HIỆN</button>
					</div>
				</div>
			</form>

			<div class="table-body">
				<form action="XoaNhieu_DQG" method="get">
					<div class="d-flex justify-content-between">
					
						<div class="form-check" onclick="chonNhieu()">
							<input class="form-check-input" type="checkbox" id="chonall">
							<label class="form-check-label" for="chonall"> Chọn Tất Cả </label>
						</div>
						
						<div class="d-flex">
							<p style="color: red; margin-right: 10px">${thongbaoxoa}</p>
							<button type="button" class="btn btn-danger checkedbtn disabled"
								data-bs-toggle="modal" data-bs-target="#showmessAll">XÓA NHIỀU</button>
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
						<c:forEach begin="1" end="${countPage}" var="i">
							<a class="${i==page?'active':''}" href="XemDS_DQG?page=${i}">${i}</a>
						</c:forEach>
					</div>
					
					<table class="table table-style">
						<thead>
							<tr>
								<th>Chọn</th>
								<th>#</th>
								<th>Tiêu Đề</th>
								<th>Tiền Quyên Góp</th>
								<th>DVTN</th>
								<th>Ngày Bắt Đầu</th>
								<th>Ngày Kết Thúc</th>
								<th>TG Còn Lại</th>
								<th>Chức Năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="dqg">
								<tr style="text-align: center;">
									<td>
										<input onclick="chonNhieu()" type="checkbox" name="chon" value="${dqg.maDot}">
									</td>
									<td>${dqg.maDot}</td>
									<td>${dqg.tieuDe}</td>
									<td><c:set var="tien" value="${dqg.tienQuyenGop}" /> <fmt:formatNumber
											var="VND" type="number" value="${tien}" /> ${VND}</td>
									<td>${dqg.dvtn.getTenDVTN()}</td>
									<td>${dqg.ngayBatDau}</td>
									<td>${dqg.ngayKetThuc}</td>
									<c:if test="${dqg.tgConLai <= 0}" var="tgConLai">
										<td style="color: red; font-size: 14px; font-weight: bold;">Đã
											Kết Thúc</td>
									</c:if>
									<c:if test="${!tgConLai}">
										<td>${dqg.tgConLai} Ngày</td>
									</c:if>

									<td class="d-flex ">
										<a class="btn btn-primary me-2" href="Sua_DQG?id=${dqg.maDot}">
											SỬA
										</a>
										<button onclick="showMessDelete(${dqg.maDot})" type="button"
											class="btn btn-danger" data-bs-toggle="modal"
											data-bs-target="#showId">XÓA</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</div>
		</div>

		<div class="modal" id="showId">
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
						<a id="yess" type="button" class="btn btn-danger">CÓ</a>
					</div>

				</div>
			</div>
		</div>

	</main>
	
	<script>
		function showMessDelete(id) {
			$('#yess').attr('href','Xoa_DQG?id=' + id);
		}
		
		function chonNhieu() {
			var checkboxAll = $('#chonall');
			var itemCheckbox = $('input[name="chon"]');
			var checkAllbtn = $('.checkedbtn');
			checkboxAll.change(function() {
				var isCheckedAll = $(this).prop('checked');
				itemCheckbox.prop('checked',isCheckedAll);
				renderCheckAll();
			})
			itemCheckbox.change(function() {
				var isCheckAll = itemCheckbox.length === $('input[name="chon"]:checked').length;
				checkboxAll.prop('checked',isCheckAll);
				renderCheckAll();
			})
			function renderCheckAll() {
				var checkCount = $('input[name="chon"]:checked').length;
				if(checkCount > 0) {
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