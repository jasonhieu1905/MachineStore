var myScroll;
var indexOfSearchItem = -1;
function loaded () {
	myScroll = new IScroll('#left-menu', {
		scrollbars: true,
		mouseWheel: true,
		interactiveScrollbars: true,
		shrinkScrollbars: 'scale',
		fadeScrollbars: false
	});
}

function topClick() {
	var top = parseInt($('.scroll').css('top')) - 70;
	var scrollH = $('.scroll').height();
	var slideH = $('.slide').height();
	$('.scroll').css('top', top);
	if (scrollH + top < slideH) {
		setTimeout(function() {
			$('.scroll').css('top', -scrollH + slideH);
		}, 600)
	}
}

function bottomClick() {
	var top = parseInt($('.scroll').css('top')) + 70;
	var scrollH = $('.scroll').height();
	var slideH = $('.slide').height();
	$('.scroll').css('top', top);
	if (scrollH - top <0 || scrollH - top > slideH) {
		setTimeout(function() {
			$('.scroll').css('top', 0);
		}, 600)
	}
}
function showImage(my) {
	var photoLink = $(my).find('img').attr('src');
	var image = $('.main-product-photo').find('.photo');
	var zoomImage = $('.main-product-photo').find('.wm-zoom-hight-img');
	image.attr('src', photoLink);
	image.attr('data-hight-src', photoLink);
	zoomImage.attr('src', photoLink);
}
$(function() {
	if (!flux.browser.supportsTransitions)
		alert("Flux Slider requires a browser that supports CSS3 transitions");

	window.f = new flux.slider('#slider', {
		autoplay : true,
		controls : true,
		width : $('.container').width() + 30,
		height : 500,
		delay : 2000
	});
});

function resizeSlider() {
    var altezzaDiv = $(window).height() - $('header').height() -
        $('footer').height()-$('body').css('margin-top').substring(0,2) - 2; //this will be the right height for the slider

    $('#slider').height(altezzaDiv);
    $('.fluxslider').height(altezzaDiv).width($('.container').width() + 30);
    $('.images').height(altezzaDiv).width($('.container').width() + 30);
    $('.image1').height(altezzaDiv).width($('.container').width() + 30);
    $('.image2').height(altezzaDiv).width($('.container').width() + 30);
}
var menuHeight = $(window).height() - 100 - 20 - 20;
$(document).ready(function() {
	
	
	getCategories(function(data){
		category = new menu({data:data,parent:"category-id"})
	});
	getAccessories(function(data){
		accessory = new menu({data:data,parent:"accessories-id"})
	})
	$("li.dropdown").hover(
			function(event) {
				var id = $(this).find(".dropdown-menu").attr('id');
				switch(id){
				case 'category-id':
					if(category){
						category.reset();
						category.show();
					}
					break;
				case 'accessories-id':
					if(accessory){
						accessory.reset();
						accessory.show();
					}
					break;
				}
			},function(event) {
				if(category){
					category.hide();
				}
				if(accessory){
					accessory.hide();
				}
			})

					$("li#searchIcon, div#searchField").hover(
							function() {
								$("div#searchField")
										.addClass("showSearchField");
								$("li#searchIcon").addClass("navy-hover");
								
							},
							function() {
								if (!$("div#searchField").hasClass("navy-hover")) {
									$("div#searchField").removeClass(
											"showSearchField");
									$("li#searchIcon").removeClass("navy-hover");
									
									$('#search-result').html("");
									$('#searchField input').val("");
								}
							})
					$("li#searchIcon").on('click', function() {
						$("div#searchField input").focus();
					})

					$("div#searchField input").focus(function() {
						
						$("div#searchField").addClass("navy-hover");

					}).focusout(function() {
						$("div#searchField").removeClass("navy-hover");
						setTimeout(function() {
							$('#search-result').html("");
							$('#searchField input').val("");
						}, 300);
						
					})
					$(document).click(function(event) {
						var self = $(event.target);
						if (self.attr('id') != 'searchField'
							&& self.parents('#searchField').length == 0
							&& self.attr('id') != 'searchIcon'
							&& self.parents('#searchIcon').length == 0)
						{
							$("div#searchField").removeClass("showSearchField");
							$("li#searchIcon").removeClass("active")
						}
					})
					
					$('#left-menu').css('max-height',menuHeight);
					//var leftMenuHeight = $('#scroller').height();	
					//if(leftMenuHeight < menuHeight){
					//	leftMenuHeight = menuHeight;
					//}
					
					var bannerHeight = 120;
					var footerHeight = $('#main-footer').outerHeight();
					var headerHeight = 120;
					if($('.banner').length > 0){
						bannerHeight = $('.banner').height() + 20 ;
					}
					
					function pageScroll(){
						var _h = $(this).scrollTop();
						var tmpMenu = $('#left-menu').height();
						if (_h > bannerHeight) {
							var total = $('body')[0].scrollHeight;
							footerHeight = $('#main-footer').outerHeight();
							
							if (_h + tmpMenu + headerHeight < total - footerHeight - 20) {
								$("#left-menu").css("top", _h + headerHeight);
							} else {

								$("#left-menu")
										.css("top", total - footerHeight - 20 - tmpMenu);
							}
						} else {
							$("#left-menu").css("top", bannerHeight + headerHeight);
						}
						
						var elId = $("[id*=product-]");
						var windows_h = $(window).height();
						
						for(var i=0;i<elId.length;i++){
							var current = elId.eq(i);
							var top = current[0].getBoundingClientRect().top;
							if(top > 0 && top  < (windows_h / 2)){
								$("[id*=catagory-]").removeClass("active");
								var catagoryId = "catagory-" + current.attr("index");												
								$("#"+catagoryId).addClass("active");
								return;
							}
						}
					}
					pageScroll();
					$(window)
							.scroll(
									function(event) {
										pageScroll();
									})

					// set min height for #main-body
					$('#main-body').css("min-height",menuHeight);
					
					// product detail
					$('#main-body .product-section .product .detail').hover(
							function() {
								$(this).find(".scroll-detail").addClass(
										"go-top");

							},
							function() {
								$(this).find(".scroll-detail").removeClass(
										"go-top");
							})

					// init zoom
					$('.main-product-photo').WMZoom({
						config : {
							stageW : 400,
							stageH : 300,
							inner : false,
							position : 'right', // [top, right, bottom, left]
							margin : 10,
							// disable inner zoom
							inner : false
						}
					});
					
					$("[id*=catagory-]").on("click touchstart",function(){
						var index = $(this).attr("index");
						var productCatagory = "product-"+index;
						var product = $("#"+productCatagory);
						var currentPage = $(this).attr("current-page");
						if(currentPage != "home"){
							var link = $(this).attr("link");
							window.location = link;
						}else{
							var body = $("html, body");
							body.stop().animate({scrollTop:product.offset().top - 100}, '600', 'swing');
						}
					});
					$("#main-products").on("click touchstart",function(){
						var link = contextPath+"/category/1";
						window.location = link;
					});
					$("#accessories").on("click touchstart",function(){
						var link = contextPath+"/category/2";
						window.location = link;
					});
					
					
					$(window).resize(resizeSlider);
					
					var searchResult = $('#search-result');
					$('#searchField input').keyup(function(event){
						
						if(event.which == 38){
							//up
							searchResult.find('li').removeClass('active');
							
							if(indexOfSearchItem > 0){
								indexOfSearchItem--;
							}
							searchResult.find('li').eq(indexOfSearchItem).addClass('active');
							
						}else if(event.which == 40){
							//down
							searchResult.find('li').removeClass('active');
							if(indexOfSearchItem < searchResult.find('li').length - 1){
								indexOfSearchItem++;
							}
							searchResult.find('li').eq(indexOfSearchItem).addClass('active');
							
						}else if(event.which == 13){
							if(searchResult.find('li.active').length > 0){
								var url = searchResult.find('li.active').find('a').attr('href');
								window.location.href = url;
							}else{
								searchFullAjax();
							}
							searchResult.find('li').removeClass('active');
						}else{
							searchResult.find('li').removeClass('active');
							searchAjax();
						}
					})
					
					loaded();
					
				})
