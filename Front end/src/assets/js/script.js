; (function ($) {
	'use strict';

	$(function () {

		document.addEventListener("touchstart", function () { }, false);

		if ('ontouchstart' in document.documentElement) {
			$('body').css('cursor', 'pointer');
		}

		/* ---------------------------------------------------- */
		/*	Isotope filter toggle								*/
		/* ---------------------------------------------------- */

		$('#options').append('<button id="toggle-nav" class="toggle-btn"></button>')

		$('#toggle-nav').on('click', function () {
			$(this).toggleClass('toggle');
			$('#filters').slideToggle();
		});

		/* ---------------------------------------------------- */
		/*	Toggle video 										*/
		/* ---------------------------------------------------- */

		$('#video-btn').on('click', function (e) {
			e.preventDefault();
			$(this).hide();
			$('.thumbnail-attachment').addClass('show-video');
		})

		/* ---------------------------------------------------- */
		/*	Social show all	event							    */
		/* ---------------------------------------------------- */

		$('#show-all').on('click', function () {

			$(this).toggleClass('clicked');

			$(this).siblings('.hide').slideToggle();

		})

		if ($('#social-fixed').length) {

			var socPopup = $('#social-fixed');

			socPopup.fadeOut();

			$(window).scroll(function () {
				var scroll = $(window).scrollTop();

				if (scroll >= 500) {
					socPopup.fadeIn();
				} else {
					socPopup.fadeOut();
				}
			});

			if ($(window).width() < 768) {
				socPopup.find('li').not(':first').addClass('hide');
			}

		}

		/* ---------------------------------------------------- */
		/*	Scroll Event									    */
		/* ---------------------------------------------------- */

		if ($('#scroll-event').length) {

			$('.page-nav').hide();

			$(window).on('scroll', function () {
				if ($(window).scrollTop() >= (($(document).height() - $(window).height()) - $('#footer').innerHeight())) {

					var href = $('.page-nav .next-page').attr('href');

					setTimeout(function () {

						location.href = href;

					}, 3000);

				}
			});

		}

		/* ---------------------------------------------------- */
		/*	Mega Menu Tabs									    */
		/* ---------------------------------------------------- */

		$('ul.tabs-caption').on('mouseover', 'li:not(.active)', function () {
			$(this)
				/*.addClass('active').siblings().removeClass('active')*/
				.closest('div.tabs').find('div.tab-content').removeClass('active').eq($(this).index()).addClass('active');
		});

		/* ------------------------------------------------
		Sticky sidebar
		------------------------------------------------ */

		$(window).on("load resize", function () {

			if ($(window).width() > 992 && $('.sticky-bar').length) {

				$('.content, .sidebar , #sidebar').theiaStickySidebar({
					// Settings
					additionalMarginTop: 30
				});

			}

		});


		/* ---------------------------------------------------- */
		/*	Banner									    		*/
		/* ---------------------------------------------------- */

		if ($('#banner-window').length) {

			var bannerWin = $('#banner-window');

			bannerWin.addClass('hide');

			if ($('.tag-wrap').length) {

				$(window).on('scroll', function () {
					var windowpos = $(window).scrollTop();
					if (windowpos > $('.tag-wrap').offset().top) {
						bannerWin.addClass('show');
						bannerWin.removeClass('hide');
					} else {
						bannerWin.removeClass('show');
						bannerWin.addClass('hide');
					}
				});

				$('.close-banner').on('click', function (e) {
					e.preventDefault();
					bannerWin.addClass('hide');
				});

			}

			$(window).on('scroll', function () {
				var windowpos = $(window).scrollTop();
				if (windowpos > 1500) {
					bannerWin.addClass('show');
					bannerWin.removeClass('hide');
				} else {
					bannerWin.removeClass('show');
					bannerWin.addClass('hide');
				}
			});

			$('.close-banner').on('click', function (e) {
				e.preventDefault();
				bannerWin.fadeOut();
			});

			$('.toggle-button').on('click', function (e) {
				e.preventDefault();
				bannerWin.toggleClass('toggle');
			});

			$(window).on('load resize', function () {
				if ($(window).width() < 768) {
					bannerWin.removeClass('toggle');
				}
			});

		}

		if ($('#popup-video').length) {

			var videoPopup = $('#popup-video');

			videoPopup.fadeOut();

			$(window).scroll(function () {
				var scroll = $(window).scrollTop();

				if (scroll >= 500) {
					videoPopup.fadeIn();
				} else {
					videoPopup.fadeOut();
				}
			});

			videoPopup.children('.item-close').on('click', function () {
				videoPopup.fadeOut();
			})

		}

		/* ---------------------------------------------------- */
		/*	Tribes									    		*/
		/* ---------------------------------------------------- */

		if ($('.tribe-events-filters').length) {

			$('#tribe_events_filters_toggle').on('click', function () {
				$('.tribe-events-filters').addClass('show-filter');
			});

			$('#tribe_events_filters_close_filters').on('click', function () {
				$('.tribe-events-filters').removeClass('show-filter');
			});

			$('.tribe-events-filters-group-heading').on('click', function () {
				$(this).parent('.tribe_events_filter_item').toggleClass('active');
			});

		}


		/* ------------------------------------------------
		Popup
		------------------------------------------------ */

		// if ($('.popup').length) {

		// 	var popup_item = $(".popup");

		// 	popup_item.not('#popup').fadeOut();

		// 	$(document).mouseup(function (e) {

		// 		var container = popup_item;
		// 		if (container.has(e.target).length === 0) {
		// 			container.fadeOut("slow");
		// 		}

		// 	});

		// 	$('.close-popup').on('click', function () {
		// 		popup_item.fadeOut("slow");
		// 	});

		// };

		/* ---------------------------------------------------- */
		/*	Custom Select										*/
		/* ---------------------------------------------------- */

		// if ($('.mad-custom-select').length) {

		// 	$.MadCustomSelects();

		// };

		/* ---------------------------------------------------- */
		/*	Tabs												*/
		/* ---------------------------------------------------- */

		// var tabs = $('.tabs-section');
		// if (tabs.length) {
		// 	tabs.tabs({
		// 		// beforeActivate: function (event, ui) {
		// 		// 	var hash = ui.newTab.children("li a").attr("href");
		// 		// },
		// 		// hide: {
		// 		// 	effect: "fadeOut",
		// 		// 	duration: 450
		// 		// },
		// 		// show: {
		// 		// 	effect: "fadeIn",
		// 		// 	duration: 450
		// 		// }
		// 		// updateHash: false
		// 	});
		// }

		/* ---------------------------------------------------- */
		/*	Newsletter											*/
		/* ---------------------------------------------------- */

		var subscribe = $('[id^="newsletter"]');
		subscribe.append('<div class="message-container-subscribe"></div>');
		var message = $('.message-container-subscribe'), text;

		subscribe.on('submit', function (e) {
			var self = $(this);

			if (self.find('input[type="email"]').val() == '') {
				text = "Please enter your e-mail!";
				message.html('<div class="alert-warning">' + text + '</div>')
					.slideDown()
					.delay(4000)
					.slideUp(function () {
						$(this).html("");
					});

			} else {
				self.find('span.error').hide();
				$.ajax({
					type: "POST",
					url: "bat/newsletter.php",
					data: self.serialize(),
					success: function (data) {
						if (data == '1') {
							text = "Your email has been sent successfully!";
							message.html('<div class="alert-success">' + text + '</div>')
								.slideDown()
								.delay(4000)
								.slideUp(function () {
									$(this).html("");
								})
								.prevAll('input[type="email"]').val("");
						} else {
							text = "Invalid email address!";
							message.html('<div class="alert-error">' + text + '</div>')
								.slideDown()
								.delay(4000)
								.slideUp(function () {
									$(this).html("");
								});
						}
					}
				});
			}
			e.preventDefault();
		});

		/* ---------------------------------------------------- */
		/*	Sticky menu											*/
		/* ---------------------------------------------------- */

		$('body').Temp({
			sticky: true
		});

		/* ---------------------------------------------------- */
		/*	Price Scale										    */
		/* ---------------------------------------------------- */

		var slider;
		if ($('#price').length) {
			slider = $('#price').slider({
				animate: true,
				range: true,
				values: [1, 99],
				min: 0,
				max: 100,
				slide: function (event, ui) {
					$('.range-values').find('.first-limit').val('$' + ui.values[0] + ',000');
					$('.range-values').find('.last-limit').val('$' + ui.values[1] + ',000');
				}
			});
		}


		/* ---------------------------------------------------- */
		/*	Accordion & Toggle									*/
		/* ---------------------------------------------------- */

		var aItem = $('.accordion:not(.toggle) .accordion-item'),
			link = aItem.find('.a-title'),
			$label = aItem.find('label'),
			aToggleItem = $('.accordion.toggle .accordion-item'),
			tLink = aToggleItem.find('.a-title');

		aItem.add(aToggleItem).children('.a-title').not('.active').next().hide();

		function triggerAccordeon($item) {
			$item
				.addClass('active')
				.next().stop().slideDown()
				.parent().siblings()
				.children('.a-title')
				.removeClass('active')
				.next().stop().slideUp();
		}


		if ($label.length) {
			$label.on('click', function () {
				triggerAccordeon($(this).closest('.a-title'))
			});
		} else {
			link.on('click', function () {
				triggerAccordeon($(this))
			});
		}

		tLink.on('click', function () {
			$(this).toggleClass('active')
				.next().stop().slideToggle();

		});

		/* ---------------------------------------------------- */
		/*	Contact Form										*/
		/* ---------------------------------------------------- */

		if ($('[id*="contact-form"]').length) {

			var cf = $('[id*="contact-form"]');
			cf.append('<div class="message-container"></div>');

			cf.on("submit", function (event) {

				var self = $(this), text;

				var request = $.ajax({
					url: "bat/mail.php",
					type: "POST",
					data: self.serialize()
				});

				request.then(function (data) {
					if (data === "1") {

						text = "Your message has been sent successfully!";

						cf.find('input:not([type="submit"]),textarea').val('');

						$('.message-container').html('<div class="alert-success"><p>' + text + '</p></div>')
							.delay(150)
							.slideDown(300)
							.delay(4000)
							.slideUp(300, function () {
								$(this).html("");
							});

					}
					else {
						if (cf.find('textarea').val().length < 20) {
							text = "Message must contain at least 20 characters!"
						}
						if (cf.find('input').val() === "") {
							text = "All required fields must be filled!";
						}
						$('.message-container').html('<div class="alert-error"><p>' + text + '</p></div>')
							.delay(150)
							.slideDown(300)
							.delay(4000)
							.slideUp(300, function () {
								$(this).html("");
							});
					}
				}, function () {
					$('.message-container').html('<div class="alert-error"><p>Connection to server failed!</p></div>')
						.delay(150)
						.slideDown(300)
						.delay(4000)
						.slideUp(300, function () {
							$(this).html("");
						});
				});

				event.preventDefault();

			});

		}

	});

})(jQuery);
