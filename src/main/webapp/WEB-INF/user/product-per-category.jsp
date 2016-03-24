<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row product-section">
	<c:if test="${currentCategory.productList.size() >0 }">
		<div class="title-container" index="${currentCategory.id}"
			id="product-${currentCategory.id}">
			<div class="title">${currentCategory.name}</div>
		</div>
		<div class="product col-xs-12">
			<c:forEach items="${currentCategory.productList}" var="product">
				<div class="col-xs-3 detail-container">
					<div class="detail">
						<div class="scroll-detail">
							<div class="info">
								<img alt="product" src="${product.image }" width="185px">
							</div>
							<div class="info cover">
								<a class="more-detail"
									href="${pageContext.request.contextPath}/detail/${currentCategory.type}/${currentCategory.id}/${product.id}">More
									detail</a>
							</div>
						</div>
					</div>
					<span class="name">${product.name}</span>
				</div>

			</c:forEach>
		</div>
	</c:if>
</div>
