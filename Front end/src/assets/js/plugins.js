;(function ($) {

    'use strict';

    $.mad_core = {

        setUp: function (options) {
            var base = this;

            var animEndEventNames = {
                    'WebkitAnimation': 'webkitAnimationEnd',
                    'OAnimation': 'oAnimationEnd',
                    'msAnimation': 'MSAnimationEnd',
                    'animation': 'animationend'
                },
                transEndEventNames = {
                    'WebkitTransition': 'webkitTransitionEnd',
                    'MozTransition': 'transitionend',
                    'OTransition': 'oTransitionEnd',
                    'msTransition': 'MSTransitionEnd',
                    'transition': 'transitionend'
                }

            base.$window = $(window);
            base.ANIMATIONEND = animEndEventNames[Modernizr.prefixed('animation')];
            base.TRANSITIONEND = transEndEventNames[Modernizr.prefixed('transition')];
            base.SUPPORT = {
                animations: Modernizr.csstransitions && Modernizr.cssanimations,
                ANIMATIONSUPPORTED: Modernizr.cssanimations,
                TRANSITIONSUPPORTED: Modernizr.csstransitions,
                ISRTL: getComputedStyle(document.body).direction === 'rtl',
                ISTOUCH: Modernizr.touch
            };
            base.XHRLEVEL2 = !!window.FormData;
            base.event = base.SUPPORT.ISTOUCH ? 'touchstart' : 'click';

            // Refresh elements
            base.refresh();
        },

        DOMLoaded: function (options) {

            var base = this;

            // set up
            base.setUp(options);

            // counters
            if ($('.counter').length) base.counters();

            // responsive menu
            if ($('#header').length) base.navInit.init(this);

            // background load
            if ($('[data-bg]').length) base.bg();

            // hidden elements init
            if ($('.hidden-section').length) base.hiddenSections();

            // side menu
            // if ($('#navbar-menu').length) base.sidebarMenu();

            // animation
            if ($('[data-appear-animation]').length) base.animate();

            // prealoader init
            if ($('#loader').length) base.preloader({
                waitAfterLoad: 1000
            });

            // init view types (product boxes)
            if ($('[data-view]').length) base.viewTypes();

        },

        elements: {
            '.main-navigation, .topbar:not(.no-mobile-advanced)': 'navMain',
            '#mobile-advanced': 'navMobile',
            '#wrapper': 'wrapper',
            '#header': 'header'
        },

        /*
        Plugin Name: 	Refresh
        */
        $: function (selector) {
            return $(selector);
        },

        refresh: function () {
            for (var key in this.elements) {
                this[this.elements[key]] = this.$(key);
            }
        },

        /**
         * Changes view type (product boxes)
         * @return jQuery collection;
         **/
        viewTypes: function () {

            var collection = $('[data-view]');
            if (!collection.length) return;

            collection.on('click', function (e) {

                e.preventDefault();
                var $this = $(this),
                    target = $($this.data('target'));

                $this
                    .addClass('active')
                    .siblings()
                    .removeClass('active');

                target
                    .removeClass('view-list view-grid view-classic')
                    .addClass('view-' + $this.data('view'));

                $.mad_core.isotope('fitRows');

            });

            return collection;

        },

        /*
		Plugin Name: 	SideMenu
		*/
        // sidebarMenu: function () {
        //
        //     var $win = $('.wrapper-container'); // or $box parent container
        //     var sideMenu = $("#navbar-menu");
        //     var wrapMenu = $(".navbar-wrap");
        //     console.log($win, sideMenu, wrapMenu)
        //     $win.on("click", function (event) {
        //         console.log('AAA')
        //         if (
        //             sideMenu.has(event.target).length === 0 //checks if descendants of $box was clicked
        //             &&
        //             !sideMenu.is(event.target) //checks if the $box itself was clicked
        //         ) {
        //             sideMenu.removeClass('open-navbar');
        //             wrapMenu.removeClass('wrap-bg');
        //         }
        //     });
        //
        //     // $('.navbar-toggle').on('click',function() {
        //     //     sideMenu.toggleClass('open-navbar');
        //     //     wrapMenu.toggleClass('wrap-bg');
        //     //     return false;
        //     // });
        //     //
        //     // $("#navbar-close").on('click',function() {
        //     //     sideMenu.removeClass('open-navbar');
        //     //     wrapMenu.removeClass('wrap-bg');
        //     //     return false;
        //     // });
        //
        // },

        /**
         * Page preloader
         * @return Object modules;
         **/
        preloader: function (options) {

            var config = {
                    waitAfterLoad: 1000,
                    duration: 1150
                },

                loader = $('#loader');

            $.extend(config, options);

            $(window).on('beforeunload load', function () {

                $('body').pageImagesLoaded().then(function () {

                    setTimeout(function () {

                        loader.addClass('hide');

                        loader.fadeOut(config.duration, function (e) {
                            loader.removeClass('hide');
                        });

                    }, config.waitAfterLoad);

                    $('.sub-menu-wrap > ul > li').not('.sub').children('a').on('click', function (e) {

                        e.preventDefault();

                        loader.addClass('show');

                        var href = $(this).attr('href');

                        setTimeout(function () {

                            location.href = href;

                        }, 1200);

                    })

                });

            });

            return this;

        },

        jQueryExtend: function () {

            $.fn.extend({

                pageImagesLoaded: function () {

                    var $imgs = this.find('img[src!=""]');

                    if (!$imgs.length) {
                        return $.Deferred().resolve().promise();
                    }

                    var dfds = [];

                    $imgs.each(function () {
                        var dfd = $.Deferred();
                        dfds.push(dfd);
                        var img = new Image();
                        img.onload = function () {
                            dfd.resolve();
                        }
                        img.onerror = function () {
                            dfd.resolve();
                        }
                        img.src = this.src;
                    });

                    return $.when.apply($, dfds);

                }

            });

        },

        /**
         Appear Animation
         **/
        animate: function () {

            $("[data-appear-animation]").each(function () {

                var self = $(this);

                self.addClass("appear-animation");

                if ($(window).width() > 1200) {
                    self.appear(function () {

                        var delay = (self.attr("data-appear-animation-delay") ? self.attr("data-appear-animation-delay") : 1);

                        if (delay > 1) self.css("animation-delay", delay + "ms");
                        self.addClass(self.attr("data-appear-animation"));

                        setTimeout(function () {
                            self.addClass("appear-animation-visible");
                        }, delay);

                    }, {accX: 0, accY: -150});
                } else {
                    self.addClass("appear-animation-visible");
                }
            });
        },

        /**
         * Emulates single accordion item
         * @param Function callback
         * @return jQuery collection;
         **/
        hiddenSections: function (callback) {

            var collection = $('.hidden-section');
            if (!collection.length) return;

            collection.each(function (i, el) {
                $(el).find('.content').hide();
            });

            collection.on('click.hidden', '.invoker', function (e) {

                e.preventDefault();

                var content = $(this).closest('.hidden-section').find('.content');

                content.slideToggle({
                    duration: 500,
                    easing: 'easeOutQuint',
                    complete: callback ? callback : function () {
                    }
                });

            });

            return collection;

        },

        /**
         Isotope
         **/
        isotope: function () {
            var self = $('[data-isotope-options]').first();
            var options = self.data('isotope-options');
            return self.isotope(options);
        },

        /**
         Adds background image
         * @return undefined;
         **/
        bg: function (collection) {

            var collection = collection ? collection : $('[data-bg]');

            collection.each(function () {

                var $this = $(this),
                    bg = $this.data('bg');

                if (bg) $this.css('background-image', 'url(' + bg + ')');

            });

        },

        navInit: {

            init: function (base) {

                this.createResponsiveButtons.call(base);
                this.navProcess(base);

                if (base.SUPPORT.ISTOUCH) {
                    this.touchNavEvent(base);
                }
            },

            touchNavEvent: function (base) {
                var clicked = false;

                $("li.menu-item-has-children > a, li.cat-parent > a, li.page-item-has-children > a").on(base.event, function (e) {
                    if (clicked != this) {
                        e.preventDefault();
                        clicked = this;
                    }
                });
            },

            navProcess: function (base) {

                base.navInit.touchNav(base, base.$window);

                $(window).resize(function (e) {
                    setTimeout(function () {
                        base.navInit.touchNav(base, e.currentTarget);
                    }, 30);
                });

            },

            touchNav: function (base, target) {

                if (base.SUPPORT.ISTOUCH || $(target).width() < 992) {

                    if (!base.navMobile.children('ul').length) {
                        base.navMobile.append(base.navMain.html());
                    }

                    base.navButton.on(base.event, function (e) {
                        e.preventDefault();

                        if (!base.wrapper.is('.active')) {
                            $('html, body').animate({scrollTop: 0}, 0, function () {
                                base.wrapper.css({
                                    height: base.navMobile.children('ul').outerHeight(true)
                                }).addClass('active');
                            });
                        }
                    });

                    // base.navHide.on(base.event, function (e) {
                    //     e.preventDefault();
                    //     if (base.wrapper.is('.active')) {
                    //         base.wrapper.css({height: 'auto'}).removeClass('active');
                    //     }
                    // });

                } else {
                    base.navMobile.children('ul').remove();
                }
            },

            createResponsiveButtons: function () {

                this.navButton = $('<button></button>', {
                    id: 'responsive-nav-button',
                    'class': 'responsive-nav-button'
                }).insertBefore(this.navMain);

                // this.navHide = $('<a></a>', {
                //     id: 'advanced-menu-hide',
                //     'href': '#'
                // }).insertBefore(this.navMobile);

            },

        }

    }

    $.mad_core.jQueryExtend();

    $(function () {

        $.mad_core.DOMLoaded();

    });

})(jQuery);

// Sticky and Go-top

(function ($, window) {

    function Temp(el, options) {
        this.el = $(el);
        this.init(options);
    }

    Temp.DEFAULTS = {
        sticky: true
    }

    Temp.prototype = {
        init: function (options) {
            var base = this;
            base.window = $(window);
            base.options = $.extend({}, Temp.DEFAULTS, options);
            base.stickyWrap = $('.sticky-header');
            base.goTop = $('<button class="go-to-top" id="go-to-top"></button>').appendTo(base.el);

            // Sticky
            if (base.options.sticky) {
                base.sticky.stickySet.call(base, base.window);
                base.stickyWrap.before($(".sticky-header").addClass("clone-fixed"));

                $(".sticky-header.clone-fixed").css('top', '-' + $('#header').outerHeight() + 'px');

                $(window).on('load resize', function () {
                    $(".sticky-header.clone-fixed").css('top', '-' + $('#header').outerHeight() + 'px');
                    $('.sticky-header.clone-fixed').css('position', 'inherit');
                });

            }

            // Scroll Event
            base.window.on('scroll', function (e) {
                // if (base.options.sticky) {
                // 	base.sticky.stickyInit.call(base, e.currentTarget);
                // }
                base.gotoTop.scrollHandler.call(base, e.currentTarget);
            });

            // Click Handler Button GotoTop
            base.gotoTop.clickHandler(base);
        },

        sticky: {

            stickySet: function () {

                // Script
                var lastScroll = 0;
                var stickyWrap = $('.sticky-header');
                $(window).on('scroll', function (data, base) {
                    var scroll = $(window).scrollTop();
                    if (lastScroll - scroll > 0) {
                        if (!stickyWrap.hasClass('sticky')) {
                            stickyWrap.addClass('sticky');
                            $('.sticky-header.clone-fixed').addClass('slideDown');
                        }
                    } else {
                        if (stickyWrap.hasClass('sticky')) {
                            stickyWrap.removeClass('sticky');
                            $('.sticky-header.clone-fixed').removeClass('slideDown');
                        }
                    }
                    lastScroll = scroll;
                    if (scroll < stickyWrap.outerHeight()) {
                        //clearHeader, not clearheader - caps H
                        $('.sticky-header.clone-fixed').css('position', 'inherit');
                    } else {
                        $('.sticky-header.clone-fixed').css('position', 'fixed');
                    }
                });

            },

        },
        gotoTop: {
            scrollHandler: function (win) {
                $(win).scrollTop() > 200 ?
                    this.goTop.addClass('go-top-visible') :
                    this.goTop.removeClass('go-top-visible');
                $('.fb-link').addClass('fb-visible');
            },
            clickHandler: function (self) {
                self.goTop.on('click', function (e) {
                    e.preventDefault();
                    $('html, body').animate({scrollTop: 0}, 800);
                });
            }
        }

    }

    /* Temp Plugin
     * ================================== */

    $.fn.Temp = function (option) {
        return this.each(function () {
            var $this = $(this), data = $this.data('Temp'),
                options = typeof option == 'object' && option;
            if (!data) {
                $this.data('Temp', new Temp(this, options));
            }
        });
    }

})(jQuery, window);
