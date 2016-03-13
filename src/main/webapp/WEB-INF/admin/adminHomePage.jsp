<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">List of products</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/addNewCategory">Add
							new category</a></li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<button id="deleteButton">Delete</button>
			</div>
		</div>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Name</th>
					<th>Priority</th>
					<th>Parent_Category</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categories}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/editCategory/${category.id}">${category.name}</a></td>
						<td>${category.priorityId}</td>
						<td>${category.parentId.name}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /. PAGE INNER  -->
</div>
<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
		});

		$('#deleteButton').click(function() {
			alert(table.rows('.selected').data().length + ' row(s) selected');
		});

	});
</script>
