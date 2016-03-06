<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Demo</title>
	<link rel="stylesheet" href="resources/css/custom-styles.css" type="text/css"></link>
	<!--[if IE]>
	<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen, projection">
	<![endif]-->
<style>

</style>
</head>
<body>
	<div>
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Menu Page -->
		<div>
			<tiles:insertAttribute name="menu" />
		</div>
		<!-- Body Page -->
		<div>
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer Page -->
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>