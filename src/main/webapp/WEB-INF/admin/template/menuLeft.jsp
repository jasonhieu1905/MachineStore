<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<nav class="navbar-default navbar-side" role="navigation">
	<div id="sideNav" href="">
		<i class="fa fa-caret-right"></i>
	</div>
	<div class="sidebar-collapse">
		<input type="hidden" value="${pageId}" id="pageId" />
		<ul class="nav" id="main-menu">
			<li><a
				href="${pageContext.request.contextPath}/listAllCategories"><i
					class="fa fa-dashboard"></i>Menu</a>
			<li><a href="${pageContext.request.contextPath}/products"><i
					class="fa fa-desktop"></i> Sản phẩm</a></li>

			<li><a href="${pageContext.request.contextPath}/contact"><i
					class="fa fa-fw fa-file"></i> Thông tin cửa hàng</a>
			<li><a href="${pageContext.request.contextPath}/adminAllAccess"><i
					class="fa fa-comment"></i> Truy cập</a>
			<li><a href="${pageContext.request.contextPath}/adminBanner"><i
					class="fa fa-comment"></i> Banner</a>
		</ul>

	</div>
</nav>

<script type="text/javascript">
	$(document).ready(
			function() {
				var idEnable = $("#pageId").val();
				$(".sidebar-collapse a").removeClass("active-menu");
				$(".sidebar-collapse a")[idEnable].setAttribute("class",
						"active-menu");
			});
</script>