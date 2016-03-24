<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row product">
	<div class="product-left">
		<div class="slide">
			<div class="controller top" onclick="topClick()">
				<i class="fa fa-angle-up fa-2x"></i>
			</div>
			<div class="scroll">
				<c:forEach items="${imageList}" var="image">
					<div class="capture" onclick="showImage(this)">
						<img alt="thumbnail" src="${image }" />
					</div>
				</c:forEach>
			</div>
			<div class="controller bottom" onclick="bottomClick()">
				<i class="fa fa-angle-down  fa-2x"></i>
			</div>
		</div>
		<div class="title">${product.name }</div>
		<div class="wm-zoom-container main-product-photo">
			<div class="wm-zoom-box">
				<img width="300px" class="photo wm-zoom-default-img"
					data-hight-src="${imageList[0]}"
					
					src="${imageList[0]}">

			</div>
		</div>
	</div>

	<div class="product-right">${product.description }</div>
</div>
<div class="row product-section">
	<c:if test="${currentCategory.productList.size() >0 }">
		<div class="title-container">
			<div class="title">The products you may want to see</div>
		</div>
		<div class="product col-xs-12">
			<c:forEach items="${currentCategory.productList}" var="product" begin="0"
				end="8">
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
		<c:if test="${currentCategory.productList.size()>8}">
			<div class="show-more-product">
				<a class="title"
					href="${pageContext.request.contextPath}/category/${currentCategory.type}/${currentCategory.id}">Show
					more product</a>
			</div>
		</c:if>
	</c:if>
</div>


















