<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left-menu" class="row">
	<div class="item first-level-head">
		<i class="fa fa-angle-double-down custom-arrow"></i>Catelogies
	</div>
	<c:forEach items="${catalogues}" var="item" begin="0" end="6">
		<c:if test="${item.productList.size()>=0 }">
			<div class="item first-level" current-page="${currentPage}" id="catagory-${item.id }"
				index="${item.id }" link="${pageContext.request.contextPath}/category/${item.type}/${item.id}">
				<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
			</div>
		</c:if>
	</c:forEach>

	<div class="item first-level-head red">
		<i class="fa fa-angle-double-down custom-arrow"></i>Accessories
	</div>
	<c:forEach items="${accessories}" var="item" begin="0" end="1">
		<c:if test="${item.productList.size()>=0 }">
			<div class="item first-level red" current-page="${currentPage}" id="catagory-${item.id }"
				index="${item.id }" link="${pageContext.request.contextPath}/category/${item.type}/${item.id}">
				<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
			</div>
		</c:if>
	</c:forEach>
</div>