<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Loại sản phẩm</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/addNewCategory">Thêm
							mới loại sản phẩm</a></li>
				</ol>
			</div>
		</div>
		<div class="row" style="margin-bottom: 10px">
			<div class="col-md-12">
				<button id="deleteButton" class="btn-info">Xoá loại sản
					phẩm đã chọn</button>
			</div>
		</div>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Tên</th>
					<th>Độ ưu tiên</th>
					<th>Thuộc về loại sản phẩm</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="category" items="${categories}">
					<tr id="${category.id}">
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
				url : 	"<%=request.getContextPath()%>/deleteCategory",
				type : "POST",
				data : {
					categoriesId: listId
				},					
				success : function(data) {
					table.rows('.selected').remove().draw( false );
				},
				error : function(result) {
					console.log(result);						
				}
			});
    
	    });
	});
	
	
</script>
