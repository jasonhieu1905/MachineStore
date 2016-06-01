<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left-menu" class="row">
	<div class="" id="scroller">
		<div class="item first-level-head">
			<i class="fa fa-angle-double-down custom-arrow"></i>Sản phẩm chính
		</div>
		<c:forEach items="${catalogues}" var="item">
			<c:if test="${item.productList.size()>=0 }">
				<div class="item first-level" current-page="${currentPage}"
					id="catagory-${item.id }" index="${item.id }"
					link="${pageContext.request.contextPath}/category/${item.type}/${item.id}">
					<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
				</div>
			</c:if>
		</c:forEach>

		<div class="item first-level-head red">
			<i class="fa fa-angle-double-down custom-arrow"></i>Phụ kiện
		</div>
		<c:forEach items="${accessories}" var="item">
			<c:if test="${item.productList.size()>=0 }">
				<div class="item first-level red" current-page="${currentPage}"
					id="catagory-${item.id }" index="${item.id }"
					link="${pageContext.request.contextPath}/category/${item.type}/${item.id}">
					<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>