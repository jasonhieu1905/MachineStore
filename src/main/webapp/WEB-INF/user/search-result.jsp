<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row product-section">
		<div class="title-container">
			<div class="title">Search Result (${products.size()})</div>
		</div>
		<div class="product col-xs-12">
			<c:forEach items="${products}" var="product">
				<div class="col-xs-3 detail-container">
					<div class="detail">
						<div class="scroll-detail">
							<div class="info">
								<img alt="product"
									src="${pageContext.request.contextPath}/resources/images/${product.image}"
									width="185px">
							</div>
							<div class="info cover">
								<a class="more-detail" href="${pageContext.request.contextPath}/detail/${product.type}/${product.categoryId}/${product.id}">More detail</a>
							</div>
						</div>
					</div>
					<div class="name">${product.name}</div>
				</div>

			</c:forEach>
		</div>
</div>
	