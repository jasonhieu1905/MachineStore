<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div id="main-footer">

	<div class="container">
		<div class="col-xs-12 col-md-7 col-lg-7 left">
			<h3>${contact.name }</h3>
			<img
				src="${pageContext.request.contextPath}/resources/images/${contact.bannerfooter}"
				width="300px" height="150px" class="store" />

			<div class="info">
				<div>
					<i class="fa fa-phone-square fa-2x"></i> <a href="tel:${contact.phone}"> ${contact.phone}</a> 
				</div>
				<div>
					<i class="fa fa-envelope fa-2x"></i> ${contact.email}
				</div>
				<div>
					<i class="fa fa-clock-o fa-2x"></i> ${contact.workingtime }
				</div>
				<div>
					<i class="fa fa-map-marker fa-2x"></i> ${contact.address }
				</div>
			</div>

		</div>
		<div class="col-xs-12 col-md-5 col-lg-5 right">
			<c:forEach var="iso" items="${fn:split(contact.isoimage, ',')}">
				<div class="thumbnail logo">
					<img
						src="${pageContext.request.contextPath}/resources/images/${iso}">
				</div>
			</c:forEach>
			<hr style="width: 100%; border-color: #A7A5A2; margin: 5PX 0PX; FLOAT: LEFT;">
			<div class="counter">
				<span style="padding: 0 10px"><i class="fa fa-users fa-2x"></i>
					Tổng lượt truy cập <b>${accessPage}</b> lượt</span>
			</div>
		</div>

	</div>
</div>