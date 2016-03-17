<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">List of products</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/addNewProduct">Thêm mới sản phẩm</a></li>
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
					<th>Tên sản phẩm</th>
					<th>Ngày tạo</th>
					<th>Menu sản phẩm</th>
					<th>Hình ảnh</th>
					<th>Độ ưu tiên</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${products}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/editProduct/${product.id}">${product.name}</a></td>
						<td>${product.createddate}</td>
						<td>${product.categoryId.name}</td>
						<td><img src="${product.image}" /></td>
						<td>${product.priorityOrder}</td>
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
