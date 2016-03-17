<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<form:form role="form" modelAttribute="currentCategory" action="${pageContext.request.contextPath}/editCategory" method="post">
			<div class="form-group">
				<label for="name">Name :</label> <input type="text"
					class="form-control" id="name"  value="${currentCategory.name}" />
			</div>
			<div class="form-group">
				<label for="priorityId">Priority :</label> <input min="0"
					type="number" class="form-control" id="priorityId"
					value="${currentCategory.priorityId}" />
			</div>
			<c:if test="${currentCategory.type == 2}">
				<div class="form-group">
					<label for="parentId">Parent category:</label> <select
						name='editCategory' class="form-control">
						<option value="${currentCategory.parentId.id}" selected>${currentCategory.parentId.name}</option>
						<c:forEach items="${categories}" var="cate">
							<c:if test="${cate.id != currentCategory.parentId.id}">
								<option value="${cate.id}">${cate.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</c:if>
			<button type="submit" class="btn btn-default">Update</button>
		</form:form>
	</div>
</div>

