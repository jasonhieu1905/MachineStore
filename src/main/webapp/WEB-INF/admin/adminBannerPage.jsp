<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- /. BODY  -->
<div id="page-wrapper">
	<div id="page-inner">
		<div class="row">
			<div class="col-md-12">
				<h1 class="page-header">Loại sản phẩm</h1>
				<ol class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath}/addNewBanner">Thêm
							mới banner</a></li>
				</ol>
			</div>
		</div>

		<div class="row" style="margin-bottom: 10px">
			<div class="col-md-12">
				<button id="deleteButton" class="btn-info">Xoá banner đã
					chọn</button>
			</div>
		</div>

		<table id="example" class="table table-striped table-bordered"
			cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>Tên</th>
					<th>Hình ảnh</th>
					<th>Độ ưu tiên</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="banner" items="${banners}">
					<tr id="${banner.id}">
						<td>${banner.name}</td>
						<td><a
							onclick="showImage('${pageContext.request.contextPath}/resources/images/${banner.image}', '${pageContext.request.contextPath}/resources/images/${banner.image}');"><img
								style="width: 100px; height: 75px"
								src="${pageContext.request.contextPath}/resources/images/${banner.image}" /></a></td>
						<td><input readonly="readonly" id="priority${banner.id}"
							onkeypress="checkEnter(${banner.id},event)"
							onclick="enableInput(${banner.id})" style="width: 30px"
							type="text" value="${banner.priority}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- /. PAGE INNER  -->
</div>
<div id="largeImgPanel" onclick="this.style.display='none'">
	<img id="largeImg" style="height: 100%; margin: 0; padding: 0;" />
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
				alert("Chưa có banner nào được chọn !");
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
				url : 	"<%=request.getContextPath()%>/deleteBanner",
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
	
	
	function enableInput(id){
		debugger;
		var input = $("#priority"+id);
		input.attr("readonly",false);
	}
	
	function checkEnter(id,event){
		var input = $("#priority"+id);
		if(isNaN(input.val())){
			alert("Chỉ được nhập số");
			return;
		}
		if(event.keyCode == 13){
			input.attr("readonly",true);
			$.ajax({
				url : 	"<%=request.getContextPath()%>/updatePriorityBanner",
				type : "POST",
				data : {
					id : id,
					priority : input.val()
				},					
				success : function(data) {
					table.rows('.selected').remove().draw( false );
				},
				error : function(result) {
					console.log(result);						
				}
			});
		}
		
	}
	
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
