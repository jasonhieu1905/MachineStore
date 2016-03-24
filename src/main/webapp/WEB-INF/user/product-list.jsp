<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${catalogues}" var="item" >
	<c:if test ="${item.productList.size() >0 }">
	<div class="title-container" index="${item.id}" id="product-${item.id}">
		<div class="title">${item.name}</div>
	</div>
	<div class="product col-xs-12">
	<c:forEach items="${item.productList}" var="product" begin="0" end="8">
		
			<div class="col-xs-3 detail-container">
				<div class="detail">
					<div class="scroll-detail">
						<div class="info">
							<img alt="product"
								src="http://www.printwand.com/blog/media/2012/01/ultimate-guide-to-your-product-launch.jpg"
								width="185px">
						</div>
						<div class="info cover">
							<span class="more-detail">More detail</span>
						</div>
					</div>
				</div>
				<span class="name">${product.name}</span>
			</div>
			
	</c:forEach>
	</div>
	<div class="show-more-product">
		<div class="title">Show more product</div>
	</div>
	</c:if>
</c:forEach>