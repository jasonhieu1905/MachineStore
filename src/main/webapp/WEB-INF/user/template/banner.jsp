<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${banners.size()>=0 }">
	<div class="banner">
		<div id="slider">
			<c:forEach items="${banners}" var="item">
				<img height="500px"
					src="${pageContext.request.contextPath}/resources/images/${item.image}"
					alt="" />
			</c:forEach>
		</div>

	</div>
</c:if>