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
			action="${pageContext.request.contextPath}/editProduct" method="post"
			enctype="multipart/form-data">
			<form:input type="hidden" path="product.id" id="productId" value="${product.id}" />
			<div class="control-group">
				<label for="name">Tên sản phẩm :</label>
				<form:input type="text" path="product.name"
					class="form-control control-group" id="name"
					value="${product.name}" />
			</div>
			<div class="form-group">
				<label>Menu sản phẩm :</label>
				<form:select class="form-control" path="product.categoryId.id">
					<c:forEach items="${categories}" var="cate">
						<c:choose>
							<c:when test="${product.categoryId.id == cate.id}">
								<option value="${cate.id}" selected="selected">${cate.name}</option>
							</c:when>
							<c:otherwise>
								<option value="${cate.id}">${cate.name}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</form:select>
			</div>

			<div class="form-group">
				<label for="type">Độ ưu tiên :</label>
				<form:input path="product.priorityOrder" type="number" />
			</div>

			<div class="form-group">
				<label>Hình chính sản phẩm</label>
				<div class="row" id="${image}">
					<img alt="" height="120px" width="220px"
						style="margin-bottom: 20px; margin-left: 10px"
						src="${pageContext.request.contextPath}/resources/images/${mainImage}" />
				</div>
				<label for="image">Thay thế hình sản phẩm chính :</label>
				<form:input id="uploadIsoImage" path="mainFileUpload.files"
					type="file" accept="image/*" />
			</div>

			<div class="form-group">
				<label>Hình chi tiết sản phẩm</label>
				<c:forEach var="image" items="${detailImages}">
					<div class="row" id="${image}">
						<div class="col-md-9 form-group">
							<img alt="" height="120px" width="220px"
								style="margin-bottom: 20px; margin-left: 10px"
								src="${pageContext.request.contextPath}/resources/images/${image}" />
						</div>
						<div class="col-md-3 form-group">
							<input type="button" class="btn-danger" value="Xoá hình"
								onclick="deleteDetailImage('${image}',this)" />
						</div>
					</div>
				</c:forEach>
				<label for="image">Thêm mới hình sản phẩm chi tiết :</label>
				<form:input id="uploadIsoImage" path="detailFileUpload.files"
					multiple="multiple" type="file" accept="image/*" />
			</div>

			<div class="form-group">
				<label for="type">Mô tả về sản phẩm :</label>
				<form:input path="product.description" name="input" type="text"
					class="jqte-test" />
			</div>
			<button type="submit" class="btn btn-default">Cập nhật sản
				phẩm</button>
		</form:form>
	</div>
</div>

<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				Bạn có chắc xoá sản phẩm đã chọn?
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary OK">OK</button>
			</div>
		</div>
	</div>
</div>

<script>
	var isoImage = "";
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
	
	function deleteDetailImage(imageName,e){
		removedElement = $(e.parentElement.parentElement);
		$("#myModal").modal({ // wire up the actual modal functionality and show the dialog
			"backdrop" : "static",
			"keyboard" : true,
			"show" : true
		});
		isoImage = imageName;
	}
	
	$("#myModal .OK").on("click", function(e) {
        $("#myModal").modal('hide');     // dismiss the dialog
        var id = $("#productId").val();
        $.ajax({
			url : 	"<%=request.getContextPath()%>/product/deleteDetailImage",
			type : "POST",
			data : {
				imageDetail : isoImage,
				id : id
			},
			success : function(data) {
				removedElement.remove();
				window.location = "<%=request.getContextPath()%>/products";
			},
			error : function(result) {
				console.log(result);
			}
		});

	});
	
	
</script>

