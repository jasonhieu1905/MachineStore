<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<form:form role="form" modelAttribute="currentCategory" action="${pageContext.request.contextPath}/editCategory" method="post">
			<form:input type="hidden" value="${currentCategory.id}" name="id" path="id" />
			<div class="form-group">
				<label for="name">Name :</label> <form:input type="text" path="name"
					class="form-control" name="name"  value="${currentCategory.name}" />
			</div>
			<div class="form-group">
				<label for="priorityId">Priority :</label> <form:input min="0"
					type="number" class="form-control" path="priorityId" id="priorityId" name="priorityId"
					value="${currentCategory.priorityId}" />
			</div>
			<c:if test="${currentCategory.type == 2}">
				<div class="form-group">
					<label for="parentId">Parent category:</label> <form:select
						name='editCategory' class="form-control"  path="parentId.id">
						<option value="${currentCategory.parentId.id}" selected>${currentCategory.parentId.name}</option>
						<c:forEach items="${categories}" var="cate">
							<c:if test="${cate.id != currentCategory.parentId.id}">
								<option value="${cate.id}">${cate.name}</option>
							</c:if>
						</c:forEach>
					</form:select>
				</div>
			</c:if>
			<form:input path="type" type="hidden" value="${currentCategory.type}" name="type" />
			<button type="submit" class="btn btn-default">Update</button>
		</form:form>
	</div>
</div>

