<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.js"></script>
<link
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.css"
	rel="stylesheet" type="text/css" />

<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Thống kê người truy cập</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<select class="dropdown" id="selectDate">
					<option value="1">Tất cả</option>
					<option value="2">Theo mốc thời gian</option>
				</select>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<div id="datepicker1" class="input-group date"
						data-date-format="dd-mm-yyyy">
						<input class="form-control" type="text" readonly /> <span
							class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="form-group">
					<div id="datepicker2" class="input-group date"
						data-date-format="dd-mm-yyyy">
						<input class="form-control" type="text" readonly /> <span
							class="input-group-addon"><i
							class="glyphicon glyphicon-calendar"></i></span>
					</div>
				</div>
			</div>
			<div class="col-md-2">
				<button type="submit" id="btnChangeAccessPage" class="btn btn-default">Cập nhật</button>
			</div>
		</div>
		<table id="example" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Ngày truy cập (Năm - tháng - ngày)</th>
					<th>Số lượng người truy cập</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="access" items="${accesses}">
					<tr id="${category.id}">
						<td>${access.accessdate}</td>
						<td>${access.accessnumber}</td>
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
<input type="hidden" value="${startDate}" id="startDate" />
<input type="hidden" value="${endDate}" id="endDate" />

<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
		});
		
		var startDate = new Date();
		var endDate = new Date();
		debugger;
		if($("#startDate").val() != ""){
			startDate = new Date(parseFloat($("#startDate").val()));
		}
		if($("#endDate").val() != ""){
			endDate = new Date(parseFloat($("#endDate").val()));
		}
		
		$("#datepicker1").datepicker({
			autoclose: true, 
			todayHighlight : true
		}).datepicker('update', startDate);
		
		;

		$("#datepicker2").datepicker({
			todayHighlight : true,
			autoclose: true

		}).datepicker('update', endDate);
		;
		
		$("#btnChangeAccessPage").click(function(){
			var start = $("#datepicker1").data().datepicker.viewDate.getTime();
			var end  = $("#datepicker2").data().datepicker.viewDate.getTime();
			var option = $("#selectDate").val();
			if(option == "1"){
				window.location = "<%=request.getContextPath()%>/adminAllAccess";			
			}else{
				window.location = "<%=request.getContextPath()%>/adminAccessByTime?startDate=" + start + "&endDate="+end;
			}
		});
	});
</script>
