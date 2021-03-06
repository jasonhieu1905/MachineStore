<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<link href="<c:url value="/resources/css/jquery-jte.css"/>"
	rel="stylesheet" />
<link href="<c:url value="/resources/css/jquery-te-1.4.0.css"/>"
	rel="stylesheet" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<script src="<c:url value="/resources/js/jquery-te-1.4.0.min.js" />"></script>
<div id="page-wrapper">
	<div id="page-inner">
		<form:form modelAttribute="productForm"
			action="${pageContext.request.contextPath}/addNewProduct"
			method="post" enctype="multipart/form-data">
			<div class="control-group">
				<label for="name">Tên sản phẩm :</label>
				<form:input type="text" path="product.name" required = "true"
					class="form-control control-group" id="name"  />
			</div>
			<div class="form-group">
				<label>Menu sản phẩm :</label>
				<form:select class="form-control" required="required" path="product.categoryId.id">
					<c:forEach items="${categories}" var="cate">
						<option value="${cate.id}">${cate.name}</option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group">
				<label for="type">Hình chính sản phẩm :</label>
				<form:input path="mainFileUpload.files" required="required" type="file" accept="image/*" />
			</div>
			<div class="form-group">
				<label for="type">Hình chi tiết sản phẩm :</label>
				<form:input path="detailFileUpload.files" multiple="multiple"
					type="file" accept="image/*" required="required" />
			</div>
			<div class="form-group">
				<label for="type">Độ ưu tiên :</label>
				<form:input path="product.priorityOrder" type="number" required="required" />
			</div>
			<div class="form-group">
				<label for="type">Mô tả về sản phẩm :</label>
				<form:input path="product.description" name="input" type="text"  class="jqte-test" required="required" />
			</div>
			<button type="submit" class="btn btn-default">Thêm mới sản
				phẩm</button>
		</form:form>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#type").change(function() {
			var value = $("#type").val();
			if (value == 1) {
				$("#parentCategory").hide();
			} else {
				$("#parentCategory").show();
			}
		});

		$('#addNewCategoryForm').validate({
			rules : {
				name : {
					required : true
				},
			},
		});
	});
	$('.jqte-test').jqte();

	// settings of status
	var jqteStatus = true;
	$(".status").click(function() {
		jqteStatus = jqteStatus ? false : true;
		$('.jqte-test').jqte({
			"status" : jqteStatus
		})
	});
</script>

