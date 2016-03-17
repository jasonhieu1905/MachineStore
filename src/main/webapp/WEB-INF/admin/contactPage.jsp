<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Thông tin cửa hàng</h1>
			</div>
		</div>
		<form>
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Tên cửa hàng: </label>
					<input type="text" class="form-control" value="${contact.name}" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Địa chỉ: </label>
					<textarea class="form-control">${contact.address}</textarea>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Số điện thoại: </label>
					<input type="text" class="form-control" value="${contact.phone}" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Email: </label>
					<input type="text" class="form-control" value="${contact.email}" />
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12 form-group">
					<label for="parentId">Thời gian làm việc: </label>
					<textarea class="form-control">${contact.workingtime}</textarea>
				</div>
			</div>
		</form>

	</div>
</div>

