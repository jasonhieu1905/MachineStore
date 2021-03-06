<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<img alt="thumbnail" src="${pageContext.request.contextPath}/resources/images/${image }" />
					</div>
				</c:forEach>
			</div>
			<div class="controller bottom" onclick="bottomClick()">
				<i class="fa fa-angle-down  fa-2x"></i>
			</div>
		</div>
		
		<div class="wm-zoom-container main-product-photo">
			<div class="wm-zoom-box">
				<img width="300px" class="photo wm-zoom-default-img"
					data-hight-src="${pageContext.request.contextPath}/resources/images/${imageList[0]}"
					
					src="${pageContext.request.contextPath}/resources/images/${imageList[0]}">

			</div>
		</div>
	</div>

	<div class="product-right">
		<div class="title">${product.name }</div>
		<div class="description">
			<c:out value="${product.description}" escapeXml="false"/>
		</div>
	</div>
</div>
<div class="row product-section">
	<c:if test="${currentCategory.productList.size() >0 }">
		<div class="title-container">
			<div class="title">Những sản phẩm liên quan</div>
		</div>
		<div class="product col-xs-12">
			<c:forEach items="${currentCategory.productList}" var="product" begin="0"
				end="7">
				<div class="col-xs-3 detail-container">
					<div class="detail">
						<div class="scroll-detail">
							<div class="info">
								<img alt="product" src="${pageContext.request.contextPath}/resources/images/${product.image }" width="185px">
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
		<c:if test="${currentCategory.productList.size()>8}">
			<div class="show-more-product">
				<a class="title"
					href="${pageContext.request.contextPath}/category/${currentCategory.type}/${currentCategory.id}">Show
					more product</a>
			</div>
		</c:if>
	</c:if>
</div>


















