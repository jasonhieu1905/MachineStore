<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>
<div id="page-wrapper">
	<div id="page-inner">
		<form:form modelAttribute="newCategory"
			action="${pageContext.request.contextPath}/addNewCategory"
			method="post" id="addNewCategoryForm">
			<div class="control-group">
				<label for="name">Name :</label>
				<form:input type="text" path="name" class="form-control control-group" id="name"
					value="${newCategory.name}" />
			</div>
			<div class="form-group">
				<label for="priorityId">Priority :</label>
				<form:input min="0" path="priorityId" type="number"
					class="form-control" id="priorityId"
					value="${newCategory.priorityId}" />
			</div>
			<div class="form-group">
				<label for="type">Type of category:</label>
				<form:select id="type" class="form-control" path="type">
					<option value="2" selected="selected">Accessories</option>
					<option value="1">Main Products</option>
				</form:select>
			</div>
			<div class="form-group" id="parentCategory">
				<label for="parentId">Parent category:</label>
				<form:select name='editCategory' class="form-control"
					path="parentId.id" id="selectParentCategory">
					<c:forEach items="${categories}" var="cate">
						<option value="${cate.id}">${cate.name}</option>
					</c:forEach>
				</form:select>
			</div>
			<button type="submit" class="btn btn-default">Add new</button>
		</form:form>
	</div>
</div>

<script>
	$(document).ready(
			function() {
				$("#type").change(function() {
					var value = $("#type").val();
					if (value == 1) {
						$("#parentCategory").hide();
						$("#selectParentCategory").val("0");
						
					} else {
						$("#parentCategory").show();
					}
				});

				$('#addNewCategoryForm').validate(
						{
							rules : {
								name : {
									required : true
								},
							},
						});
			});
</script>

