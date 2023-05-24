<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>




<!-- start - navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<button class="navbar-toggler me-3" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#offcanvasExample"
			aria-controls="offcanvasExample">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand me-auto" href="#"><img
			src="images/img-admin.png"></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarAdmin" aria-controls="navbarAdmin"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarAdmin">
			<div class="d-flex ms-auto">
				<p class="m-auto" style="color: white;">${sessionScope.tkAdminSession.tenDangNhap}</p>
				<ul class="navbar-nav m-auto mb-lg-0">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							<i class="bi bi-person-fill"></i>
					</a>
						<ul class="dropdown-menu dropdown-menu-end"
							aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="ThayDoiMatKhau">Thay đổi MK</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="DangXuat">Đăng xuát</a></li>
						</ul></li>
				</ul>
			</div>

		</div>
	</div>
</nav>
<!-- end - navbar -->

<!-- start - offcanvas -->
<div class="offcanvas offcanvas-start sidebar-nav bg-dark" tabindex="-1"
	id="offcanvasExample">
	<div class="container mt-3">
		<div class="dashboard">
			<a href="#"> <i class="bi bi-speedometer2"></i> <span>Dashboard</span>
			</a>
		</div>

		<hr class="bg-light" />

		<div id="accordion">
			<ul class="dashboard-menu">
				<li><a href="QuanTri.jsp">Giao Diện Người Dùng</a></li>
				<li><a class="d-flex" data-bs-toggle="collapse"
					href="#dotquyengop"> Đợt Quyên Góp <span
						class="ms-auto right-icon"> <i class="bi bi-chevron-down"></i>
					</span>
				</a>
					<div id="dotquyengop" class="collapse" data-bs-parent="#accordion">
						<ul>
							<li><a href="Them_DQG">Thêm</a></li>
							<li><a href="XemDS_DQG">Xem Tất Cả</a></li>
						</ul>
					</div></li>
				<li><a class="d-flex" data-bs-toggle="collapse" href="#dvtn">
						Đơn Vị Tiếp Nhận <span class="ms-auto right-icon"> <i
							class="bi bi-chevron-down"></i>
					</span>
				</a>
					<div id="dvtn" class="collapse" data-bs-parent="#accordion">
						<ul>
							<li><a href="Them_DVTN">Thêm</a></li>
							<li><a href="XemDS_DVTN">Xem All</a></li>
						</ul>
					</div></li>
				<li><a class="d-flex" data-bs-toggle="collapse"
					href="#nguoidung"> Người Dùng <span class="ms-auto right-icon">
							<i class="bi bi-chevron-down"></i>
					</span>
				</a>
					<div id="nguoidung" class="collapse" data-bs-parent="#accordion">
						<ul>
							<li><a href="Them_TK">Thêm</a></li>
							<li><a href="XemDS_TK">Xem Tất Cả</a></li>
						</ul>
					</div></li>
				<li><a class="d-flex" data-bs-toggle="collapse" href="#thongke">
						Thống Kê <span class="ms-auto right-icon"> <i
							class="bi bi-chevron-down"></i>
					</span>
				</a>
					<div id="thongke" class="collapse" data-bs-parent="#accordion">
						<ul class="sub-menu">
							<li><a href="ThongKe_DQG">Đợt Quyên Góp</a></li>
							<li><a href="ThongKe_TK">Người Dùng</a></li>
						</ul>
					</div></li>

			</ul>
		</div>
	</div>
</div>
<!-- end - offcanvas -->

<!-- table-main -->

<!-- table-main -->
