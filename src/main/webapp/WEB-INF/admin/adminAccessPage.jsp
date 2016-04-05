<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Thống kê người truy cập</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">
				<select class="dropdown">
					<option value="1">Tất cả</option>
					<option value="2">Theo mốc thời gian</option>
				</select>
			</div>
			<div class="col-md-5">
				<div class="input-append date form_datetime">
					<input size="16" type="text" value="" readonly> <span
						class="add-on"><i class="icon-th"></i></span>
				</div>
			</div>
			<div class="col-md-5">
				<div class="input-append date form_datetime">
					<input size="16" type="text" value="" readonly> <span
						class="add-on"><i class="icon-th"></i></span>
				</div>
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


<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#example').DataTable();

		$('#example tbody').on('click', 'tr', function() {
			$(this).toggleClass('selected');
		});

		$(".form_datetime").datetimepicker({
			format : "dd MM yyyy - hh:ii"
		});

	});
</script>
