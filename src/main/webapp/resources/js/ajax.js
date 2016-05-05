function searchAjax() {
	var data = {}
	var tmp = $('#searchField input').val();
	if (tmp) {
		data["query"] = tmp;
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : contextPath+"/search/auto",
			data : JSON.stringify(data),
			success : function(data) {
				console.log("SUCCESS: ", data);
				// display(data);
				var result = "";
				for ( var p in data) {
					var path = contextPath + '/detail/'+data[p].type+'/'+data[p].categoryId+'/'+data[p].id;
					result += "<li><a href='"+path+"'>" + data[p].name
							+ " - <span class='catagory-name'>"
							+ data[p].catagoryName + "</span></a></li>";
				}
				$('#search-result').html(result);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				// display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
	}else{
		$('#search-result').html("");
	}
}

//search/full
function searchFullAjax() {
	var data = {}
	var tmp = $('#searchField input').val();
	
	
	if (tmp) {
		var url = contextPath+"/search/full?query="+tmp;
		window.location.href = url;
	}
}

