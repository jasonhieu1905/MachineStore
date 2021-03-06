<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row product-section">
	<c:if test="${currentCategory.productList.size() >0 }">
		<c:if test="${currentPage == 'product' }">
			<div class="title-container" index="${currentCategory.id}"
				id="product-${currentCategory.id}">
				<div class="title">${currentCategory.name}</div>
			</div>
		</c:if>
		<c:if test="${currentPage == 'accessories' }">
			<div class="title-container red" index="${currentCategory.id}"
				id="product-${currentCategory.id}">
				<div class="title red">${currentCategory.name}</div>
			</div>
		</c:if>
		
		
		<div class="product col-xs-12">
			<c:forEach items="${currentCategory.productList}" var="product">
				<div class="col-xs-3 detail-container">
					<div class="detail">
						<div class="scroll-detail">
							<div class="info">
								<img alt="product" src="${pageContext.request.contextPath}/resources/thumbnail/${product.image }" width="185px">
							</div>
							<div class="info cover">
								<a class="more-detail"
									href="${pageContext.request.contextPath}/detail/${currentCategory.type}/${currentCategory.id}/${product.id}">Xem chi tiết</a>
							</div>
						</div>
					</div>
					<div class="name">${product.name}</div>
				</div>

			</c:forEach>
		</div>
	</c:if>
</div>
