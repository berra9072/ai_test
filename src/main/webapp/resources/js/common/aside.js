if (typeof(aircode) == "undefined") {aircode = {};}
if (typeof(aircode.aside) == "undefined") {aircode.aside = {};}

(function (_context_) {

	var bindEvent = function() {
		$("#lnb").on("click", ".list_menu .title a", function() {
			var $this = $(this);

			var url = $this.data("url");
			if (url == "/dashboard") {
				location.href = url;
			}

			var $parentMenu = $this.closest(".list_menu");
			if($parentMenu.hasClass("cur")) {
				return false;
			}

			if (!$parentMenu.hasClass("on")) {
				var $lastSelectMenu = $("#lnb").find(".list_menu.on").not(".cur");
				$lastSelectMenu.removeClass("on").addClass("hide");
				$lastSelectMenu.find(".subMenu").slideUp("fast");
			}

			if ( $parentMenu.hasClass("hide")){
				if ($parentMenu.find("li").length > 0) {
					$parentMenu.find(".subMenu").slideDown("fast");
				}
				$parentMenu.removeClass("hide").addClass("on");
			} else if ( $parentMenu.hasClass("on")){
				$parentMenu.find(".subMenu").slideUp("fast");
				$parentMenu.removeClass("on").addClass("hide");
			}
			return false;
		});
	};

	var drawFocus = function(){
		var url = location.pathname;

		// 하위페이지(상세)인 경우 목록페이지 url로 focus 표시
		if (url.indexOf("/channel/") > -1) {
			url = "/channel/list";
		}

		var $lnb = $("#lnb");

		$lnb.find("a").filter("[href='" + url + "']").parent().addClass("on")
			.closest("li.list_menu").addClass("on").addClass("cur").removeClass("hide");
	};

	// 하위 메뉴가 없는 ul태그삭제
	var removeEmptyUl = function(){
		var $leftMenu = $("#lnb .list_menu");
		$.each($leftMenu, function(idx, obj){
			var $obj = $(obj);
			if ($obj.find("li").length < 1) {
				$obj.find("ul").remove();
			}
		});
	};

	_context_.initialize = function () {
		removeEmptyUl();
		drawFocus();
		bindEvent();
	};

	$(document).ready(function () {
		_context_.initialize();
	});

})(aircode.aside);

