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
		width : 1200,
		height : 500,
		delay : 2000
	});
});
var menuHeight = $(window).height() - 120 - 20;
$(document)
		.ready(
				function() {
					$("li.dropdown")
							.hover(
									function(event) {
										var toggle = $(this);
										toggle.find(".header-menu-container")
												.css("height", "");
										toggle.addClass("active");
										toggle
												.find(".dropdown-menu")
												.toggleClass("sub-menu flipInX");
										toggle
												.find(".header-menu-container")
												.css(
														"height",
														toggle
																.find(
																		".header-menu-container")
																.height());
										setTimeout(function() {
											toggle.find(".dropdown-menu")
													.toggleClass("flipInX");
										}, 600);
									},
									function(event) {
										var toggle = $(this);
										toggle.removeClass("active");
										if (toggle.find(".dropdown-menu")
												.hasClass("sub-menu")) {
											toggle.find(
													".header-menu-container")
													.css("height", 0);
											toggle
													.find(
															".header-menu-container")
													.on(
															'transitionend webkitTransitionEnd oTransitionEnd',
															function() {
																toggle
																		.find(
																				".header-menu-container")
																		.css(
																				"height",
																				"");
																toggle
																		.find(
																				".dropdown-menu")
																		.removeClass(
																				"sub-menu");
															});
										}

									})

					$("li#searchIcon, div#searchField").hover(
							function() {
								$("div#searchField")
										.addClass("showSearchField");
								$("li#searchIcon").addClass("active")
							},
							function() {
								if (!$("div#searchField").hasClass("active")) {
									$("div#searchField").removeClass(
											"showSearchField");
									$("li#searchIcon").removeClass("active")
								}
							})
					$("li#searchIcon").on('click', function() {
						$("div#searchField input").focus();
					})

					$("div#searchField input").focus(function() {
						$("div#searchField").addClass("active");

					}).focusout(function() {
						$("div#searchField").removeClass("active");
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
					$(window)
							.scroll(
									function(event) {
										var _h = $(this).scrollTop();
										if (_h > 520) {
											if ($('body')[0].scrollHeight > _h
													+ 500 + 120 + 20 + leftMenuHeight) {
												$("#left-menu").css("top",
														_h + 120);
											} else {
												$("#left-menu")
														.css(
																"top",
																$('body')[0].scrollHeight - 500 - 20 - leftMenuHeight);
											}
										} else {
											$("#left-menu").css("top", 640);
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
				})
