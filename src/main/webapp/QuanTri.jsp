<%@page import="bean.DotQuyenGop"%>
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

<title>Quản Trị</title>
</head>
<body>
	<%
	Object tkAdminSession = session.getAttribute("tkAdminSession");
	if(tkAdminSession == null) {
		response.sendRedirect("DangNhap.jsp");
	} else {
	%>
	<jsp:include page="admin.jsp"></jsp:include>

	<!-- table-main -->
	<main>
		<div class="container-fuild">
			<iframe src="home" width="100%" height="700px" style="border: 1px solid white;"></iframe>
		</div>
	</main>
	<!-- table -->
	<% } %>
</body>
</html>