<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="navbar-default navbar-side" role="navigation">
	<div id="sideNav" href="">
		<i class="fa fa-caret-right"></i>
	</div>
	<input type="hidden" value="${id-enable}" id="id-enable" />
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">

			<li><a
				href="${pageContext.request.contextPath}/listAllCategories/0"><i
					class="fa fa-dashboard"></i>Menu</a>
			<li><a href="${pageContext.request.contextPath}/products/1"><i
					class="fa fa-desktop"></i> Sản phẩm</a></li>

			<li><a href="${pageContext.request.contextPath}/contact/2"><i
					class="fa fa-fw fa-file"></i> Thông tin cửa hàng</a>
			<li><a
				href="${pageContext.request.contextPath}/adminAllAccess/3"><i
					class="fa fa-comment"></i> Truy cập</a>
			<li><a
				href="${pageContext.request.contextPath}/adminBanner/4"><i
					class="fa fa-comment"></i> Banner</a>
		</ul>

	</div>
</nav>

<script type="text/javascript">
	$(document).ready(
			function() {
				var idEnable = $("#id-enable").val();
				$(".sidebar-collapse a").removeClass("active-menu");
				$(".sidebar-collapse a")[idEnable].setAttribute("class",
						"active-menu");
			});
</script>