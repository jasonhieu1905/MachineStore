<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hybrid Bootstrap Admin Template</title>
    <!-- Bootstrap Styles-->
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="<c:url value="/resources/css/font-awesome.css"/>" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="<c:url value="/resources/css/custom-styles.css"/>" rel="stylesheet" />
    <!-- Google Fonts-->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' /> 
   
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