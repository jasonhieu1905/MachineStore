<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left-menu" class="row">
	<c:forEach items="${menu}" var="item">
		<div class="item first-level">
			<i class="fa fa-angle-double-down custom-arrow"></i>${item.title.name }
		</div>
		<c:forEach items="${item.items}" var="sub">
			<div class="item second-level">
				<i class="fa fa-long-arrow-right custom-arrow"></i> ${sub.name }
			</div>

		</c:forEach>
	</c:forEach>
</div>