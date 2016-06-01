<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Hộp thư khách hàng</h1>
			</div>
		</div>
		<div class="row" style="margin-bottom: 10px">
			<div class="col-md-12">
				<button id="deleteButton" class="btn-info">Xoá Message đã
					chọn</button>
			</div>
		</div>

		<table id="example" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Tên khách hàng</th>
					<th>Số điện thoại</th>
					<th>Email</th>
					<th>Nội dung</th>
					<th>Ngày tạo</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="message" items="${messages}">
					<tr id="${message.id}">
						<td>${message.name}</td>
						<td>${message.phone}</td>
						<td>${message.email}</td>
						<td>${message.content}</td>
						<td>${message.createddate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				Bạn có chắc xoá message đã chọn?
			</div>
			<!-- dialog buttons -->
			<div class="modal-footer">
				<button type="button" class="btn btn-primary OK">OK</button>
			</div>
		</div>
	</div>
</div>

<!-- set up the modal to start hidden and fade in and out -->
<div id="myModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				Bạn có chắc xoá message đã chọn?
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
				alert("Chưa có message nào được chọn !");
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
				url : 	"<%=request.getContextPath()%>/deleteMessage",
				type : "POST",
				data : {
					listId: listId
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
