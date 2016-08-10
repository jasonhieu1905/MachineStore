<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<style type="text/css">
#map {
	margin-top: 5%;
	width: 100%;
	height: 400px;
}
</style>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"></script>
</head>
<div>
	<input type="hidden" value="${contact.mapX}" id="mapX" /> <input
		type="hidden" value="${contact.mapY}" id="mapY" /> <input
		type="hidden" value="${contact.name}" id="name" /> <input
		type="hidden" value="${contact.zoommap}" id="zoommap" />
	

	<div class="row" style="margin-top: 20px">
		<div class="col-md-6">
			<div id="map"></div>
			<script
				src="https://maps.googleapis.com/maps/api/js?callback=initMap" async
				defer></script>

			<script type="text/javascript">
				function initMap() {
					var mapDiv = document.getElementById('map');
					var mapX = $("#mapX").val();
					var mapY = $("#mapY").val();
					var name = $("#name").val();
					var zoommap = $("#zoommap").val();
					var map = new google.maps.Map(mapDiv, {
						center : {
							lat : parseFloat(mapX),
							lng : parseFloat(mapY)
						},
						zoom : parseInt(zoommap)
					});
					var marker = new google.maps.Marker({
						position : {
							lat : parseFloat(mapX),
							lng : parseFloat(mapY)
						},
						map : map,
						title : name
					});
				}
			</script>
		</div>
		<div class="col-md-6">
			<div class="col-md-7">
				<h2 style="margin-bottom: 20px">Liên hệ với chúng tôi</h2>
				<form:form modelAttribute="message" id="form-contact"
					action="${pageContext.request.contextPath}/addNewMessage"
					method="post">
					<div class="form-group">
						<label for="Contact">Họ và tên :</label>
						<form:input min="0" path="name" type="text" required="required"
							class="form-control" id="username" value="${message.name}" />
					</div>
					<div class="form-group">
						<label for="Contact">Email :</label>
						<form:input path="email" id="email" type="email"
							class="form-control" required="required" value="${message.email}" />
					</div>
					<div class="form-group">
						<label for="Contact">Phone :</label>
						<form:input path="phone" id="phone" type="number"
							class="form-control" required="required" value="${message.phone}" />
					</div>
					<div class="form-group">
						<label for="Contact">Viết bình luận :</label>
						<form:textarea path="content" id="comment" type="text"
							required="required" class="form-control"
							value="${message.content}" />
					</div>
					<button type="submit" id="submitButton" class="btn btn-default">Gởi
						liên hệ</button>
				</form:form>
			</div>
			<div class="col-md-5">
				<div class="row" style="margin-top: 20px">
					<div class="col-md-12">
						<img height="100px"
							src="${pageContext.request.contextPath}/resources/images/logo-fixed.png" />
					</div>
				</div>
				<div class="row" style="margin-top: 10px; margin-left: 10px">
					<i class="fa fa-map-marker" style="font-size: xx-large"
						aria-hidden="true"></i> ${contact.address}
				</div>
				<div class="row" style="margin-top: 10px; margin-left: 10px">
					<i class="fa fa-phone" style="font-size: xx-large"
						aria-hidden="true"></i><a href="tel:${contact.phone}">
						${contact.phone}</a>
				</div>
				<div class="row" style="margin-top: 10px; margin-left: 10px">
					<i class="fa fa-envelope-o" style="font-size: xx-large"
						aria-hidden="true"></i> ${contact.email}
				</div>
			</div>
		</div>

	</div>
</div>
<div id="myModal" class="modal fade" style="margin-top: 20%">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				Xin hãy điền đầy đủ thông tin
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary OK">OK</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$("#submitButton").on(
			"click",
			function(event) {
				if (!($("#username").val()) || !($("#email").val())
						|| !($("#phone").val()) || !($("#comment").val())) {
					$("#myModal").modal({ // wire up the actual modal functionality and show the dialog
						"backdrop" : "static",
						"keyboard" : true,
						"show" : true
					});
					event.preventDefault();
				}
			});

	$("#myModal .OK").on("click", function(e) {
		$("#myModal").modal('hide');
	});
</script>
