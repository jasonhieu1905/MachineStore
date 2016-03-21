<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Thông tin cửa hàng</h1>
			</div>
		</div>
		<form id="contactEditForm"
			action="${pageContext.request.contextPath}/contact" role="form"
			method="POST">
			<input type="hidden" name="id" value="${contact.id}" />
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Tên cửa hàng: </label> <input type="text"
						class="form-control" name="name" value="${contact.name}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Đia chỉ: </label>
					<textarea class="form-control" name="address">${contact.address}</textarea>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Số điện thoại: </label> <input type="text"
						class="form-control" name="phone" value="${contact.phone}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Email: </label> <input name="email"
						type="text" class="form-control" value="${contact.email}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Thời gian làm việc: </label>
					<textarea name="workingtime" class="form-control">${contact.workingtime}</textarea>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 form-group">
					<label for="parentId">Hoành độ: </label> <input name="mapX"
						class="form-control" value="${contact.mapX}" />
				</div>
				<div class="col-md-6 form-group">
					<label for="parentId">Tung độ: </label> <input name="mapY"
						class="form-control" value="${contact.mapY}" />
				</div>
			</div>

			<div class="row">
				<label for="isoImage">Iso : </label>
				<c:forEach var="image" items="${images}">
					<img alt="" height="200px" width="200px"
						style="margin-bottom: 20px; margin-left: 10px"
						src="<c:url value='/resources/images/${image}'/>" />
				</c:forEach>
			</div>

			<div class="row">
				<form action="${pageContext.request.contextPath}/admin/uploadFile"
					method="POST" enctype="multipart/form-data">
					<input type="file" multiple="multiple" class="form-control" />
					<input type="submit"  class="btn btn-default" value="Upload File"/>
				</form>
			</div>

			<div class="row" style="margin-top: 20px">
				<button type="submit" class="btn btn-default">Update</button>
			</div>

		</form>

	</div>
</div>

