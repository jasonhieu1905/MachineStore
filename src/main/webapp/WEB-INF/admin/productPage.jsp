<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<head>
<style type="text/css">
#largeImgPanel {
	text-align: center;
	display: none;
	position: fixed;
	z-index: 100;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(100, 100, 100, 0.5);
}
</style>
</head>

<div id="largeImgPanel" onclick="this.style.display='none'">
	<img id="largeImg" style="height: 100%; margin: 0; padding: 0;" />
</div>
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Loại sản phẩm</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/addNewProduct">Thêm
							mới loại sản phẩm</a></li>
				</ol>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<button id="deleteButton" style="margin-bottom: 10px"
					class="btn-info">Xoá sản phẩm đã chọn</button>
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
					<tr id=${product.id}>
						<td><a
							href="${pageContext.request.contextPath}/editProduct/${product.id}">${product.name}</a></td>
						<td>${product.createddate}</td>
						<td>${product.categoryId.name}</td>
						<td><a
							onclick="showImage('${pageContext.request.contextPath}/resources/images/${product.image}', '${pageContext.request.contextPath}/resources/images/${product.image}');">
								<img style="width: 50px; height: 50px"
								src="${pageContext.request.contextPath}/resources/images/${product.image}" />
						</a></td>
						<td>${product.priorityOrder}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /. PAGE INNER  -->
</div>

<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				Bạn có chắc xoá sản phẩm đã chọn?
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary OK">OK</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
		});

		$('#deleteButton').click(function() {
			var selectedItem = table.rows('.selected').data().length;
			console.log(selectedItem);
			if(selectedItem == 0){
				alert("Chưa có sản phẩm nào được chọn !");
				return;
			}
			$("#myModal").modal({ // wire up the actual modal functionality and show the dialog
				"backdrop" : "static",
				"keyboard" : true,
				"show" : true
			});
		});
		
		
		$("#myModal .OK").on("click", function(e) {
	        $("#myModal").modal('hide');     // dismiss the dialog
	        var selectedItem = table.rows('.selected').data();
	        var listId = "";
	        for(i = 0; i < selectedItem.length;i++){
	        	listId += selectedItem[i].DT_RowId + ",";
	        }
	        $.ajax({
				url : 	"<%=request.getContextPath()%>/deleteProduct",
				type : "POST",
				data : {
					productsId : listId
				},
				success : function(data) {
					table.rows('.selected').remove().draw(false);
				},
				error : function(result) {
					console.log(result);
				}
			});
		});

	});

	function showImage(smSrc, lgSrc) {
		document.getElementById('largeImg').src = smSrc;
		showLargeImagePanel();
		unselectAll();
		setTimeout(function() {
			document.getElementById('largeImg').src = lgSrc;
		}, 1)
	}

	function showLargeImagePanel() {
		document.getElementById('largeImgPanel').style.display = 'block';
	}
</script>
