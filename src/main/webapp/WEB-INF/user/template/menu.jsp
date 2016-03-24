<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="left-menu" class="row">
	<div class="item first-level-head">
			<i class="fa fa-angle-double-down custom-arrow"></i>Catelogies
	</div>
	<c:forEach items="${catalogues}" var="item" >
		<div class="item first-level" id="catagory-${item.id }" index="${item.id }">
			<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
		</div>
	</c:forEach>
	
	<div class="item first-level-head red">
			<i class="fa fa-angle-double-down custom-arrow"></i>Accessories
	</div>
	<c:forEach items="${accessories}" var="item">
		<div class="item first-level red">
			<i class="fa fa-chevron-circle-right  custom-arrow"></i>${item.name}
		</div>
	</c:forEach>
</div>