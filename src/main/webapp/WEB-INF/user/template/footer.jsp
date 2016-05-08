<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<div id="main-footer">

	<div class="container">
		<div class="col-xs-12 col-md-7 col-lg-7 left">
			<h3>${contact.name }</h3>
			<img src="http://ownastore.hallmark.com/wp-content/uploads/2012/12/poster-store-landing-1360x598.jpg"
			width="300px" height="150px" class="store"/>
			
			<div class="info">
				<div><i class="fa fa-phone-square fa-2x"></i> ${contact.phone }</div>
				<div><i class="fa fa-envelope fa-2x"></i> ${contact.email}</div>
				<div><i class="fa fa-clock-o fa-2x"></i> ${contact.workingtime }</div>
				<div><i class="fa fa-map-marker fa-2x"></i> ${contact.address }</div>
			</div>
			
		</div>
		<div class="col-xs-12 col-md-5 col-lg-5 right">
			<div class="thumbnail logo">
				<img src="${pageContext.request.contextPath}/resources/images/isoImage1.jpg">
			</div>
			<div class="thumbnail logo">
				<img src="${pageContext.request.contextPath}/resources/images/isoImage2.jpg">
			</div><div class="thumbnail logo">
				<img src="${pageContext.request.contextPath}/resources/images/isoImage3.png">
			</div>
			<hr style="    width: 100%;border-color: #A7A5A2;margin:5PX 0PX;FLOAT: LEFT;">
			<div class="counter">
				<span style="padding: 0 10px"><i class="fa fa-user fa-2x"></i> Hôm nay <b>3000</b> lượt </span>
				<span style="padding: 0 10px"><i class="fa fa-users fa-2x"></i> Tổng cộng  <b>222000</b> lượt</span
			</div>
		</div>
		
		
		
		
		
	</div>
</div>