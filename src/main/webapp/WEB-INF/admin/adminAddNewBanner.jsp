<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<form:form role="form" modelAttribute="bannerForm" enctype="multipart/form-data" action="${pageContext.request.contextPath}/addNewBanner" method="post">
			<div class="form-group">
				<label for="name">Tên hình :</label> <form:input type="text" path="banner.name"
					class="form-control"  value="${bannerForm.banner.name}" />
			</div>
			<div class="form-group">
				<label for="priorityId">Độ ưu tiên :</label> <form:input min="0"
					type="number" class="form-control" path="banner.priority"  name="priority"
					value="${bannerForm.banner.priority}" />
			</div>
			<div class="form-group">
				<label for="image">Hình ảnh :</label> 
				<form:input path="fileUpload.files"  type="file" multiple="multiple" accept="image/*" />
			</div>
			
			<button type="submit" class="btn btn-default">Thêm mới</button>
		</form:form>
	</div>
</div>

