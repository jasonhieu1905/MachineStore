<!-- <div style="width:300px;height:100px;background-color:grey" class="flipInX animated"> navbar-fixed-top-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navy navy-header navy-fix-top">
	<div class="container">
		<div class="row">
			<a class="navy-logo" href="#"><img src="${pageContext.request.contextPath}/resources/img/logo.png" /></a>
			<ul>
				<li id="searchIcon"><a><i class="fa fa-search"></i></a></li>

				<li class="${currentPage == 'home'? 'active': ''}"><a
					href="${pageContext.request.contextPath}/home"><i class="fa fa-home"></i><span class="navy-text">Home
							page</span></a></li>
				<li class="dropdown ${currentPage == 'product'? 'active': ''}"><a
					href="#" class="dropdown-toggle"><i
						class="fa fa-barcode "></i><span class="navy-text">Main
							Product</span></a>
					<div class="dropdown-menu animated main">
						<div class="header-menu-container row">
							<c:forEach items="${catalogues}" var="item" begin="0" end="18">
								<a href="${pageContext.request.contextPath}/category/${item.type}/${item.id}" class="item"> <i
									class="fa fa-angle-right"></i> ${item.name }
								</a>
							</c:forEach>
							<c:if test="${catalogues.size() > 19 }">
								<div class="item show-more">show more...</div>
							</c:if>
							<hr style="float: left; width: 100%" />
						</div>
					</div></li>
				<li class="dropdown ${currentPage == 'accessories'? 'active': ''}"><a href="#" class="dropdown-toggle"> <i
						class="fa fa-suitcase "></i><span class="navy-text">Accessories</span></a>
					<div class="dropdown-menu animated main">
						<div class="header-menu-container row">
							<c:forEach items="${accessories}" var="item" begin="0" end="18">
								<a href="${pageContext.request.contextPath}/category/${item.type}/${item.id}" class="item"> <i
									class="fa fa-angle-right"></i> ${item.name }
								</a>
							</c:forEach>
							<c:if test="${accessories.size() > 19 }">
								<div class="item show-more">show more...</div>
							</c:if>
							<hr style="float: left; width: 100%" />
						</div>
					</div></li>
				<li class="${currentPage == 'contact'? 'active': ''}"><a href="${pageContext.request.contextPath}/contact"><i class="fa fa-phone"></i><span
						class="navy-text">Contact</span></a></li>
			</ul>
		</div>
	</div>
</div>
<div id="searchField" class="search-field container">
	<input type="text" />
	<button class="btn btn-primary">Search</button>
</div>