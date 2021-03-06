<!-- <div style="width:300px;height:100px;background-color:grey" class="flipInX animated"> navbar-fixed-top-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navy navy-header navy-fix-top">
	<div class="container">
		<div class="row">
			<a class="navy-logo" href="${pageContext.request.contextPath}/home"><img width="150px" src="${pageContext.request.contextPath}/resources/images/logo-fixed.png" /></a>
			<ul>
				<li id="searchIcon"><a><i class="fa fa-search"></i></a></li>

				<li class="${currentPage == 'home'? 'active': ''}"><a
					href="${pageContext.request.contextPath}/home"><i class="fa fa-home"></i><span class="navy-text">Trang chủ</span></a></li>
				<li class="dropdown ${currentPage == 'product'? 'active': ''}"><a
					href="#" class="dropdown-toggle"><i
						class="fa fa-barcode "></i><span class="navy-text">Sản phẩm chính</span></a>
					<div class="dropdown-menu sub-menu" id="category-id">
						<div class="scroller">
						 </div>
					</div>
				</li>
				<li class="dropdown ${currentPage == 'accessories'? 'active': ''}"><a href="#" class="dropdown-toggle"> <i
						class="fa fa-suitcase "></i><span class="navy-text">Phụ kiện</span></a>
					<div class="dropdown-menu sub-menu" id="accessories-id">
						<div class="scroller">
							
						 </div>
					</div>
				</li>
				<li class="${currentPage == 'contact'? 'active': ''}"><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-phone"></i><span
						class="navy-text">Liên hệ</span></a></li>
			</ul>
		</div>
	</div>
</div>
<div id="searchField" class="search-field container">
	<input type="text" />
	<button class="btn btn-primary" onclick="searchFullAjax()">Search</button>
	<ul class="search-result" id="search-result">
		
	</ul>
</div>