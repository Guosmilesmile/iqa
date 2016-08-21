(function ($, window, undefined) {
	'use strict';

	var $doc = $(document),
	Modernizr = window.Modernizr;

	$(document).ready(function () {
		//Foundation Components auto initializtion
		$.fn.foundationAlerts ? $doc.foundationAlerts() : null;
		$.fn.foundationButtons ? $doc.foundationButtons() : null;
		$.fn.foundationAccordion ? $doc.foundationAccordion() : null;
		$.fn.foundationNavigation ? $doc.foundationNavigation() : null;
		$.fn.foundationTopBar ? $doc.foundationTopBar() : null;
		$.fn.foundationCustomForms ? $doc.foundationCustomForms() : null;
		$.fn.foundationMediaQueryViewer ? $doc.foundationMediaQueryViewer() : null;
		$.fn.foundationTabs ? $doc.foundationTabs({
			callback : $.foundation.customForms.appendCustomMarkup
		}) : null;
		$.fn.foundationTooltips ? $doc.foundationTooltips() : null;
		$.fn.foundationMagellan ? $doc.foundationMagellan() : null;
		$.fn.foundationClearing ? $doc.foundationClearing() : null;

		$.fn.placeholder ? $('input, textarea').placeholder() : null;

		//Initial slide
		$(".slide-wrapper").orbit({
			animation : 'fade',
			animationSpeed : 1000, // how fast animations are
			timer : true, // display timer?
			advanceSpeed : 5000,
			bullets : true,
			resetTimerOnClick : true
		});

		//Initial News

		$.when(
			load($('#newsTextList1'), 'data/news_text_list.html?abc=' + Math.random().toString()),
			load($('#newsImageList'), 'data/news_image_list.html?abc=' + Math.random().toString()))
		.always(function () {
			load($('#events'), 'data/events.html?abc=' + Math.random().toString());
		});
		

		//Fix IE 8-6 Body Width
		if ($("html").is(".lt-ie9")) {
			var resizer = function () {
				var width = $(window).width();
				if (width < 1020)
					$("#wrapper,#warn").width(1020);
				else
					$("#wrapper,#warn").width(width);

			};
			$(window).load(resizer);
			$(window).resize(resizer);
		}
	});

	// Hide address bar on mobile devices (except if #hash present, so we don't mess up deep linking).
	if (Modernizr.touch && !window.location.hash) {
		$(window).load(function () {
			setTimeout(function () {
				window.scrollTo(0, 1);
			}, 0);
		});
	}
})(jQuery, this);

function load($el, url, callback) {
	var defer = $.Deferred();

	if (!($el && $el instanceof jQuery))
		return;
	if (!$el.is(".loading"))
		$el.addClass("loading");

	var tries = 0;

	var _load = function () {
		$el.load(url, function (response, status) {
			if (status == "error" && tries < 3) {
				setTimeout(function () {
					_load();
					tries++;
				}, 1000);
			} else if (status == "success") {
				if (callback)
					callback.apply(this, arguments);
				if ($el.is(".loading"))
					$el.removeClass("loading");
				defer.resolve(arguments);
			} else {
				defer.reject();
			}
		});
	}

	_load();

	return defer.promise();
}
