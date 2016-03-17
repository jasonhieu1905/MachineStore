<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="navbar-default navbar-side" role="navigation">
	<div id="sideNav" href="">
		<i class="fa fa-caret-right"></i>
	</div>
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">

			<li><a class="active-menu" href="${pageContext.request.contextPath}/listAllCategories"><i
					class="fa fa-dashboard"></i>Menu</a>
			<li><a href="${pageContext.request.contextPath}/products"><i class="fa fa-desktop"></i>
					Sản phẩm</a></li>

			<li><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-fw fa-file"></i>
					Thông tin cửa hàng</a>
		</ul>

	</div>
</nav>