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

function getCategories(callback) {
		$.ajax({
			type : "GET",
			contentType : "application/json",
			url : contextPath+"/menu/catagories",
			success : function(data) {
				console.log("SUCCESS: ", data);
				callback(data);
				
			},
			error : function(e) {
				console.log("ERROR: ", e);
				// display(e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
}

function getAccessories(callback) {
	$.ajax({
		type : "GET",
		contentType : "application/json",
		url : contextPath+"/menu/accessories",
		success : function(data) {
			console.log("SUCCESS: ", data);
			callback(data);
			
		},
		error : function(e) {
			console.log("ERROR: ", e);
			// display(e);
		},
		done : function(e) {
			console.log("DONE");
		}
	});
}


function menu(config){
	
	var index = 0;
	var left = 0;
	var itemH = 36;
	var itemW = 200;
	var max = 20;
	var parent={h:itemH * (max /2) + 50 + 20,w:itemW * 2};
	var scroller={h:itemH * (max /2) + 20,w:itemW * 2};
	var len = config.data.length;
	var col = Math.ceil(len / max);
	if(col == 1){
		if(len < max /2){
			parent.h = len * itemH + 50 + 20;
			parent.w = itemW;
			scroller.h = len * itemH + 20;
			scroller.w = itemW;
		}
	}else if(col > 1){
		scroller.w = (itemW * 2) * col + 20;
	}
	
	this.next = function(){
		
		if(index < col -1){
			index++;
			left -= 100;
			containerScroller.css('left',left+"%");
			
			
		}
	}
	this.back = function(){
		if(index > 0){
			index--;
			left += 100;
			containerScroller.css('left',left+"%");
		}
	}
	
	var result="";
	for ( var c in config.data) {
		var current = config.data[c];
		var path = contextPath + '/category/'+current.type+'/'+current.id;
		if((c/(max/2) % 2) == 1 &&  parseInt(c) % (max/2) == 0){
			result +="<div style='width:1px;height:100%;background-color:rgba(255,255,255,.5)'></div>";
		}
		result += "<a href='"+path+"' class='item'><i class='fa fa-dot-circle-o '></i>" 
				+ current.name
				+"</a>";
	}
	var container = $('#'+config.parent);
	var containerScroller = container.find('.scroller');
	container.css('width',parent.w);
	container.css('height',0);
	containerScroller.css('left',0);
	containerScroller.css('width',scroller.w);
	containerScroller.css('height',scroller.h);
	containerScroller.html(result);
	if(col > 1){
		container.append("<div class='swipe'><hr/><i class='fa fa-angle-left fa-2x left ' onclick='category.back()'></i><i class='fa fa-angle-right fa-2x right' onclick='category.next()'></i></div>");
	}else{
		container.append("<div class='swipe'><hr/></div>");
	}
	
	this.show = function(){
		container.css('height',parent.h);
	}
	this.hide = function(){
		container.css('height',0);
	}
	this.reset = function(){
		index = 0;
		left = 0;
		containerScroller.css('left',0);
	}
}
