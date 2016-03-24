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
	if (scrollH - top > slideH) {
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
		delay : 5000
	});
});
var menuHeight = $(window).height() - 120 - 20;
$(document).ready(function() {
	$("li.dropdown").hover(
			function(event) {
				var toggle = $(this);
				toggle.find(".dropdown-menu").css("height", "");
				toggle.addClass("navy-hover");
				toggle.find(".dropdown-menu").toggleClass("sub-menu flipInX");
				toggle.find(".dropdown-menu").css("height",toggle.find(".dropdown-menu").height());
				setTimeout(function() {
					toggle.find(".dropdown-menu").toggleClass("flipInX");
					}, 600);
				},function(event) {
					var toggle = $(this);
					toggle.removeClass("navy-hover");
					if (toggle.find(".dropdown-menu").hasClass("sub-menu")) {
						toggle.find(".dropdown-menu").css("height", 0);
						toggle.find(".dropdown-menu").on('transitionend webkitTransitionEnd oTransitionEnd',
								function() {
							toggle.find(".dropdown-menu").css("height","");
							toggle.find(".dropdown-menu").removeClass("sub-menu");
							});
						}
					})

					$("li#searchIcon, div#searchField").hover(
							function() {
								$("div#searchField")
										.addClass("showSearchField");
								$("li#searchIcon").addClass("navy-hover")
							},
							function() {
								if (!$("div#searchField").hasClass("navy-hover")) {
									$("div#searchField").removeClass(
											"showSearchField");
									$("li#searchIcon").removeClass("navy-hover")
								}
							})
					$("li#searchIcon").on('click', function() {
						$("div#searchField input").focus();
					})

					$("div#searchField input").focus(function() {
						$("div#searchField").addClass("navy-hover");

					}).focusout(function() {
						$("div#searchField").removeClass("navy-hover");
					})
					$(document)
							.click(
									function(event) {
										var self = $(event.target);
										if (self.attr('id') != 'searchField'
												&& self.parents('#searchField').length == 0
												&& self.attr('id') != 'searchIcon'
												&& self.parents('#searchIcon').length == 0) {
											$("div#searchField").removeClass(
													"showSearchField");
											$("li#searchIcon").removeClass(
													"active")
										}
									})
									
					var leftMenuHeight = $('#left-menu').height();	
					var bannerHeight = 0;
					var footerHeight = 520;
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
														.css(
																"top",
																$('body')[0].scrollHeight - footerHeight - leftMenuHeight);
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
					$('#main-body').css("min-height",
							$('#left-menu').outerHeight() + 20);

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
					$('#left-menu').css('max-height',menuHeight);
					$("[id*=catagory-]").on("click",function(){
						var index = $(this).attr("index");
						var productCatagory = "product-"+index;
						var product = $("#"+productCatagory);
						product.scrollIntoView();
					});
					$(window).resize(resizeSlider);

					function resizeSlider() {
					    var altezzaDiv = $(window).height() - $('header').height() -
					        $('footer').height()-$('body').css('margin-top').substring(0,2) - 2; //this will be the right height for the slider

					    $('#slider').height(altezzaDiv);
					    $('.fluxslider').height(altezzaDiv).width($('.container').width() + 30);
					    $('.images').height(altezzaDiv).width($('.container').width() + 30);
					    $('.image1').height(altezzaDiv).width($('.container').width() + 30);
					    $('.image2').height(altezzaDiv).width($('.container').width() + 30);

					}
					
					
				})
