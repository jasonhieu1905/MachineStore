<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<form:form id="myform" role="form" modelAttribute="user"
			action="${pageContext.request.contextPath}/updateAdminInfo"
			method="post">
			<form:input type="hidden" value="${user.id}" name="id" path="id" />
			<div class="form-group">
				<label for="name">Name :</label>
				<form:input type="text" id="username" path="username" class="form-control"
					name="name" value="${user.username}" />
			</div>
			<div class="form-group">
				<label for="name">Password :</label>
				<form:input id="password" type="password" path="password"
					class="form-control" name="name" value="${user.password}" />
			</div>
			<div class="form-group">
				<label for="name">Lặp lại password :</label>
				<input id="passwordRetype" type="password"
					class="form-control" name="name" value="${user.password}" />
			</div>
			<button type="submit" id="updatePassword" class="btn btn-default">Update</button>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#passwordRetype").val($("#password").val());
		$("#myform").submit(function(event) {
			if($("#username").val() == ""){
				alert("Username không được trống");
				event.preventDefault();
				return ;
			}
			if($("#password").val() == ""){
				alert("Password không được trống");
				event.preventDefault();
				return ;
			}
			if ($("#password").val() != $("#passwordRetype").val()) {
				alert("2 password không khớp nhau !!!")
				event.preventDefault();
			}
		});
	});
</script>

