var myScroll;
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
var menuHeight = $(window).height() - 120 - 20;
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
					var leftMenuHeight = $('#left-menu').height();	
					if(leftMenuHeight > menuHeight){
						leftMenuHeight = menuHeight;
					}
					
					var bannerHeight = 0;
					var footerHeight = $('#main-footer').outerHeight();
					var headerHeight = 120;
					if($('.banner').length > 0){
						bannerHeight = $('.banner').height() + 20;
					}
					$("#left-menu").css("top", bannerHeight + 120);
					$(window)
							.scroll(
									function(event) {
										var _h = $(this).scrollTop();
										if (_h > bannerHeight) {
											if ($('body')[0].scrollHeight > _h
													+ footerHeight + headerHeight + leftMenuHeight) {
												$("#left-menu").css("top",
														_h + headerHeight);
											} else {
												$("#left-menu")
														.css("top",($('body')[0].scrollHeight - footerHeight - leftMenuHeight - 80));
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
									})

					// set min height for menu
					if($('#left-menu').outerHeight() + 20 > 500){
						$('#main-body').css("min-height",
								$('#left-menu').outerHeight() + 20);
					}
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
					
					$("[id*=catagory-]").on("click",function(){
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
							//$(window).scrollTop(product.offset().top);
							//product[0].scrollIntoView();
						}
					});
					$(window).resize(resizeSlider);
					
					$('#searchField input').keyup(function(event){
						searchAjax();
					})
					
					loaded();
					document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
					
					
					
				})
