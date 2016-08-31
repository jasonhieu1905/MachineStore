<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Thành Mỹ</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-header.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-styles.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-user-style.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-product.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.wm-zoom-1.0.css"
	type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-slider.css"
	type="text/css"></link>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom-footer.css"
	type="text/css"></link>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />	
<script>var contextPath = "${pageContext.request.contextPath}"</script>
<!--[if IE]>
	<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen, projection"></link>
	<![endif]-->
</head>

<body>
	<!-- Header -->
	<tiles:insertAttribute name="header" />
	<!-- Menu Page -->

	<!-- Body Page -->
	<div class="body-fix">
		<tiles:insertAttribute name="banner" />
		<div class="container">
			<tiles:insertAttribute name="menu" />
			<div id="main-body">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
	<!-- Footer Page -->

	<tiles:insertAttribute name="footer" />

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.wm-zoom-1.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/flux.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/iscroll.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ajax.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/user-custom.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.scrollIntoView.min.js"></script>
</body>
</html>