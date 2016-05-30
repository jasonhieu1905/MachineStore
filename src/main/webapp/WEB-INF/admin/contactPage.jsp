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
		<form:form modelAttribute="contactForm" id="contactEditForm"
			enctype="multipart/form-data"
			action="${pageContext.request.contextPath}/admin/contact"
			method="POST">
			<form:input type="hidden" path="contact.id" name="id"
				value="${contact.id}" />
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Tên cửa hàng: </label>
					<form:input required="required" path="contact.name" type="text" class="form-control"
						name="name" value="${contact.name}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Đia chỉ: </label>
					<form:textarea required="required" path="contact.address" class="form-control"
						value="${contact.address}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Số điện thoại: </label>
					<form:input required="required" type="text" class="form-control" path="contact.phone"
						name="phone" value="${contact.phone}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Email: </label>
					<form:input required="required" name="email" path="contact.email" type="email"
						class="form-control" value="${contact.email}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Thời gian làm việc: </label>
					<form:textarea required="required" path="contact.workingtime" name="workingtime"
						class="form-control" value="${contact.workingtime}" />
				</div>
			</div>

			<div class="row">
				<div class="col-md-6 form-group">
					<label for="parentId">Hoành độ: </label>
					<form:input required="required" path="contact.mapX" name="mapX" class="form-control"
						value="${contact.mapX}" />
				</div>
				<div class="col-md-6 form-group">
					<label for="parentId">Tung độ: </label>
					<form:input required="required" path="contact.mapY" name="mapY" class="form-control"
						value="${contact.mapY}" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="zoomMap">Zoom google map: </label> 
					<form:input required="required" path="contact.zoommap" class="form-control" type="number"
						value="${contact.zoommap}" />
				</div>
			</div>

			<div class="row">
				<label for="isoImage">Iso : </label>
			</div>
			<c:if test="${not empty images}">
				<c:forEach var="image" items="${images}">
					<div class="row" id="${image}">
						<div class="col-md-9 form-group">
							<img alt="" height="120px" width="220px"
								style="margin-bottom: 20px; margin-left: 10px"
								src="${pageContext.request.contextPath}/resources/images/${image}" />
						</div>
						<div class="col-md-3 form-group">
							<input type="button" class="btn-danger" value="Xoá hình"
								onclick="deleteIsoImage('${image}',this)" />
						</div>
					</div>
				</c:forEach>
			</c:if>
			<form:input type="hidden" path="contact.isoimage" />
			<div class="form-group">
				<label for="image">Thêm mới Iso :</label>
				<form:input id="uploadIsoImage" path="fileUpload.files"
					multiple="multiple" type="file" accept="image/*" />
			</div>
			
			<div class="form-group">
				<label>Banner Footer</label>
				<div class="row" id="${image}">
					<img alt="" height="120px" width="220px"
						style="margin-bottom: 20px; margin-left: 10px"
						src="${pageContext.request.contextPath}/resources/images/${contact.bannerfooter}" />
				</div>
				<label for="image">Thay thế banner footer :</label>
				<form:input id="uploadBannerFooter" path="bannerFooterFileUpload.files"
					type="file" accept="image/*" />
			</div>

			<div class="row" style="margin-top: 20px">
				<button type="submit" class="btn btn-default">Cập nhật</button>
			</div>

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

<script type="text/javascript">
	var isoImage = "";
	var removedElement;
	function deleteIsoImage(imageName,e){
		removedElement = $(e.parentElement.parentElement);
		$("#myModal").modal({ // wire up the actual modal functionality and show the dialog
			"backdrop" : "static",
			"keyboard" : true,
			"show" : true
		});
		isoImage = imageName;
		
	}
	
	function deleteBannerImage(bannerFooter,e)
	
	$("#myModal .OK").on("click", function(e) {
        $("#myModal").modal('hide');     // dismiss the dialog
        $.ajax({
			url : 	"<%=request.getContextPath()%>/contact/deleteIsoImage",
			type : "POST",
			data : {
				imageDeleted : isoImage
			},
			success : function(data) {
				removedElement.remove();
				window.location = "<%=request.getContextPath()%>/admin/contact";
									},
									error : function(result) {
										console.log(result);
									}
								});

					});
</script>
