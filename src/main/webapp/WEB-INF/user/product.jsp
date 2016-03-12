<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row product">
	<c:if test="${name == 'nam'}">
		NAM
	</c:if>
	<div class="product-left">
		<div class="slide">
			<div class="controller top" onclick="topClick()">
				<i class="fa fa-angle-up fa-2x"></i>
			</div>
			<div class="scroll">
				<div class="capture" onclick="showImage(this)">
					<img  alt="ABC"
						src="http://waptai.com/wp-content/uploads/beautiful-girl-hd-wallpaper-3.jpg">
				</div>
				<div class="capture" onclick="showImage(this)">
					<img alt="ABC"
						src="http://hdwallpaperbackgrounds.net/wp-content/uploads/2015/08/Most-Beautiful-Girls-Desktop-Wallpapers.jpg">
				</div>
				<div class="capture" onclick="showImage(this)">
					<img alt="ABC"
						src="https://static.pexels.com/photos/6403/people-woman-girl-technology.jpg">
				</div>
				<div class="capture" onclick="showImage(this)">
					<img alt="ABC"
						src="http://xemtinvl.com/wp-content/uploads/2015/09/Hot-girl-sao-Viet-xinh-dep-nhung-chua-tot-nghiep-cap-3-3-1380611565212.jpg">
				</div>
				<div class="capture" onclick="showImage(this)">
					<img alt="ABC"
						src="http://images-cdn.9gag.com/photo/anB8WVV_700b.jpg">
				</div>
			</div>
			<div class="controller bottom" onclick="bottomClick()">
				<i class="fa fa-angle-down  fa-2x"></i>
			</div>
		</div>
		<div class="title">Product Tile</div>
		<div class="wm-zoom-container main-product-photo">
			<div class="wm-zoom-box">
				<img width="300px" class="photo wm-zoom-default-img"
					data-hight-src="http://xemtinvl.com/wp-content/uploads/2015/09/Hot-girl-sao-Viet-xinh-dep-nhung-chua-tot-nghiep-cap-3-3-1380611565212.jpg"
					data-loader-src="resources/img/loader.gif"
					src="http://xemtinvl.com/wp-content/uploads/2015/09/Hot-girl-sao-Viet-xinh-dep-nhung-chua-tot-nghiep-cap-3-3-1380611565212.jpg">

			</div>
		</div>
	</div>

	<div class="product-right">
		Product Information not valid The
		following product information programmed into the system board is
		missing or invalid. System Board (00A) - Product Configuration
	</div>
</div>
<div class="row product-section">
	<jsp:include page="product-list.jsp"></jsp:include>
	<jsp:include page="product-list.jsp"></jsp:include>
</div>


















