
//placeHolder
jQuery.fn.textPlaceholder = function () {	return this.each(function(){		var that = this;		if (that.placeholder && 'placeholder' in document.createElement(that.tagName)) return;		var placeholder = that.getAttribute('placeholder');		var input = jQuery(that);		if (that.value === '' || that.value == placeholder) {			input.addClass('text-placeholder');			that.value = placeholder;		}		input.focus(function(){			if (input.hasClass('text-placeholder')) {				this.value = '';				input.removeClass('text-placeholder')			}		});		input.blur(function(){			if (this.value === '') {				input.addClass('text-placeholder');				this.value = placeholder;			} else {				input.removeClass('text-placeholder');			}		});		that.form && jQuery(that.form).submit(function(){			if (input.hasClass('text-placeholder')) {				that.value = '';			}		});	});};


/**
 * 부모창 변경시 현재 팝업창 닫기
 */
function openerStatusCheck(ori_opener_key) {

	if(opener.opener_key == null || ori_opener_key != opener.opener_key){
		alert("작업중이던 페이지가 변경되어 현재창을 종료합니다.");
		self.close();
	}
}

/**
 * @module [공통] openLayer
 * @description 오픈 레이어 팝업
 * @author 이민호
 * @example
 * openLayer(selector);
 */
window.openLayer = function(selector){
	$("body").append('<div class="dim"></div>');
	$("body").find('.dim').fadeIn(300);
	$(selector).fadeIn(300);
}

/**
 * @module [공통] closeLayer
 * @description 클로즈 레이어 팝업
 * @author 이민호
 * @example
 * closeLayer(selector);
 */
window.closeLayer = function(selector){
	$("body").find('.dim').hide().remove();
	$(selector).hide();
}

/**
 * @module [공통] openLayer
 * @description 오픈 레이어 팝업
 * @author 이민호
 * @example
 * openLayer(selector);
 */
window.openLayerInfo = function(selector){
	$(selector).fadeIn(300);
}

/**
 * @module [공통] closeLayer
 * @description 클로즈 레이어 팝업
 * @author 이민호
 * @example
 * closeLayer(selector);
 */
window.closeLayerInfo = function(selector){
	$("body").find('.dim').hide().remove();
	$(selector).hide();
}


/**
 * spinner(로딩)
 * showSpinner(); hideSpinner();
 */
var showSpinner = function() {
	var $body = $("body");
//	$body.prepend('<div class="dim"></div><div class="loader"></div>');
	$body.prepend('<div class="loading"></div>');
//	$body.find('.dim').fadeIn(100);
};
var hideSpinner = function() {
	var $body = $("body");
//	$body.find('.dim').hide().remove();
	$body.find('.loading').hide().remove();
};

/**
 * 파일 업로드 확장자 체크
 */
function fileTypeChk(obj,allowFileExt) {
	var file = $(obj).val();
	var fileExt = file.slice(file.lastIndexOf(".")+1).toLowerCase();
	var fileExtOkYn = "N";
	var allowFileExtArr  = allowFileExt.split(",");
	for(var i =0 ; i < allowFileExtArr.length; i++){
		if((allowFileExtArr[i]).replace(/^\s*|\s*$/g, '')==fileExt.replace(/^\s*|\s*$/g, '')){
			fileExtOkYn = "Y";
		}
	}
	if( fileExtOkYn == "N" ){
		alert( fileExt+" 형식의 파일은 등록 불가합니다.");
	    $(obj).replaceWith( $(obj).clone(true) );
		return;
	}
}

/**
 * 좌우 공백 제거
 */
function trimRL(obj) {
	$(obj).val($(obj).val().replace(/^\s*|\s*$/g, ''));
}

// 현재시간 가져오기( YYYY-MM-DD hh:mm:ss 포맷 )
function getTimeStampYMDhms() {
	var d = new Date();

	var s =
    leadingZeros(d.getFullYear(), 4) + '-' +
    leadingZeros(d.getMonth() + 1, 2) + '-' +
    leadingZeros(d.getDate(), 2) + ' ' +

    leadingZeros(d.getHours(), 2) + ':' +
    leadingZeros(d.getMinutes(), 2) + ':' +
    leadingZeros(d.getSeconds(), 2);

	return s;
}

/**
 * digits 길이만큼 문자열 앞에 '0' 삽입
 */
function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

/**
 * AJAX 에러 메세지
 */
function ajaxErrorChk(x, e) {

	if (x.status == 0) {
    	alert('You are offline!!\nPlease Check Your Network.');
    } else if (x.status == 404) {
      	alert('Requested URL not found.');
    } else if (x.status == 500) {
      	alert('Internel Server Error.');
    } else if (x.status == 901) {
    	alert('Session Time out.');
    	window.location.replace(adminUrl + '/cms/login');
    } else if (e == 'parsererror') {
      	alert('Error\nParsing JSON Request failed.');
    } else if (e == 'timeout') {
      	alert('Request Time out.');
    } else {
      	alert('Unknow Error\n' + x.responseText);
    }
}

/**
 * 숫자키 입력체크
 */
function numChk(event) {
	if (event.keyCode >= 48 && event.keyCode <= 57) {
        return true;
    } else {
    	event.returnValue = false;
    }
}

/**
 * 현재일시
 */
var comDate = {
	getYear: function() {
		var date = new Date();
		return date.getFullYear();
	},
	getMonth: function() {
		var date = new Date();
		return date.format("MM");
	},
	getDays: function() {
		var date = new Date();
		return date.format("dd");
	},
	getHours: function() {
		var date = new Date();
		return date.format("HH");
	},
	getMinutes: function() {
		var date = new Date();
		return date.format("mm");
	},
	getSecond: function() {
		var date = new Date();
		return date.format("ss");
	},
	getLastDays: function(yyyy, mm) {
		var lastDay = ( new Date( yyyy, mm, 0) ).getDate();
		return lastDay;
	},
	getToday: function(format) {
		var date = new Date();
		return date.format(format);
	}
};

var resData = {
	toform: function(data, form) {
		for(var p in data) {
			var obj = form.find('[name="'+p+'"]');

			if (!obj){
				continue;
			}

		    obj.each(function(i) {
			    try{
					if (this.type == 'checkbox' || this.type == 'radio') {
				    	if (this.value == data[p]){
				    		this.checked = true;
				    	}
					}else{
						$(this).val(data[p]).change();
					}
		    	}catch(e){

		    	}
		    });
		}
	}
};

/**
 * 두 날짜 사이 날짜배열
 * @param 시작일, 종료일
 * @returns []
 */
function getDateRangeArray(start, end, f) {
	var dateArray = [];

	if (!f || f == "") f = "yyyy-MM-dd";

	var endDate = new Date(end);
	var curDate = new Date(start);

	while (curDate <= endDate) {
		dateArray.push(curDate.format(f));
		curDate = curDate.addDays(1);
	}

	return dateArray;
}

/**
 * Date Day Add
 * @param number
 * @returns Date
 */
Date.prototype.addDays = function(days) {
	var date = new Date(this.valueOf());
	date.setDate(date.getDate() + days);
	return date;
};


/**
 * Date format
 * @param f
 * @returns {*}
 */
Date.prototype.format = function (f) {
	if (!this.valueOf()) return " ";
	if (!f || f == "") f = "yyyy-MM-dd";

	var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
	var weekShortName = ["일", "월", "화", "수", "목", "금", "토"];
	var d = this;

	return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function ($1) {
		switch ($1) {
			case "yyyy": return d.getFullYear();
			case "yy": return (d.getFullYear() % 1000).zf(2);
			case "MM": return (d.getMonth() + 1).zf(2);
			case "dd": return d.getDate().zf(2);
			case "E": return weekName[d.getDay()];
			case "e": return weekShortName[d.getDay()];
			case "HH": return d.getHours().zf(2);
			case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
			case "mm": return d.getMinutes().zf(2);
			case "ss": return d.getSeconds().zf(2);
			case "a/p": return d.getHours() < 12 ? "오전" : "오후";
			default: return $1;
		}
	});
};

/**
 * 초를 시,분,초로 변경
 * @param f
 * @returns {*}
 */
String.prototype.toHHMMSS = function (f) {
	if (!this.valueOf()) return " ";
	if (!f || f == "") f = "hh:mm:ss";

    var sec = parseInt(this, 10);
    var h   = Math.floor(sec / 3600);
    var m = Math.floor((sec - (h * 3600)) / 60);
    var s = sec - (h * 3600) - (m * 60);

    return f.replace(/(hh|mm|ss)/gi, function ($1) {
		switch ($1) {
			case "hh": return h.zf(2);
			case "mm": return m.zf(2);
			case "ss": return s.zf(2);
			default: return $1;
		}
	});
};

/**
 * 초를 분,초로 변경
 * @param f
 * @returns {*}
 */
String.prototype.toMMSS = function (f) {
	if (!this.valueOf()) return " ";
	if (!f || f == "") f = "mm:ss";

    var sec = parseInt(this, 10);
    var m = Math.floor(sec / 60);
    var s = sec - (m * 60);

    return f.replace(/(mm|ss)/gi, function ($1) {
		switch ($1) {
			case "mm": return m.zf(2);
			case "ss": return s.zf(2);
			default: return $1;
		}
	});
};

/**
 * 000000 시분초포맷
 * @param f
 * @returns string
 */
String.prototype.formatHHMMSS = function(f) {
	if (!this.valueOf()) return " ";
	if (this.length != 6) return " ";
	if (!f || f == "") f = "hh:mm:ss";

	var h = this.substring(0, 2);
	var m = this.substring(2, 4);
	var s = this.substring(4);

	return f.replace(/(hh|mm|ss)/gi, function ($1) {
		switch ($1) {
			case "hh": return h;
			case "mm": return m;
			case "ss": return s;
			default: return $1;
		}
	});
};

/**
 * 000000 시분포맷
 * @param f
 * @returns string
 */
String.prototype.formatHHMM = function(f) {
	if (!this.valueOf()) return " ";
	if (this.length != 6) return " ";
	if (!f || f == "") f = "hh:mm";

	var h = this.substring(0, 2);
	var m = this.substring(2, 4);

	return f.replace(/(hh|mm)/gi, function ($1) {
		switch ($1) {
			case "hh": return h;
			case "mm": return m;
			default: return $1;
		}
	});
};

/**
 * 한자리일경우 앞에 0을 붙여준다.
 * @param len
 * @returns {string}
 */
String.prototype.string = function(len){
	var s = '', i = 0;
	while (i++ < len) { s += this; }
	return s;
};
String.prototype.zf = function(len){ return "0".string(len - this.length) + this; };
Number.prototype.zf = function(len){ return this.toString().zf(len); };

//string 20180406112345 to f format date
String.prototype.toDateFormat = function(f){
	var year = this.substring(0, 4);
	var month = this.substring(4, 6)-1;
	var day = this.substring(6, 8);
	var hour = this.substring(8, 10);
	var min = this.substring(10, 12);
	var second = this.substring(12, 14);
	return new Date(year,month,day,hour,min,second).format(f);
};

/**
 * 시분초를 초로 변경
 * @param
 * @returns {*}
 */
String.prototype.toSec = function() {
	var value = this;

	var hh = parseInt(value.substr(0, 2), 10);
	var mm = parseInt(value.substr(2, 2), 10);
	var ss = parseInt(value.substr(4, 2), 10);

	return hh * 3600 + mm * 60 + ss;
};

/**
 * 콤마
 * @returns {string}
 */
String.prototype.toNumberFormat = function() {
	var value = this;
	var arrValue = value.split('.');
	var x1 = arrValue[0];
	var x2 = arrValue.length > 1 ? '.' + arrValue[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
};
Number.prototype.toNumberFormat = function() {
	return this.toString().toNumberFormat();
};

function dateAddDel(sDate, nNum, type) {

    var yy = parseInt(sDate.substr(0, 4), 10);
    var mm = parseInt(sDate.substr(5, 2), 10);
    var dd = parseInt(sDate.substr(8, 2), 10);
    var hh = 0;
    if(type == "h") hh = parseInt(sDate.substr(11, 2), 10)
    else hh = parseInt("00", 10);

    if (type == "d") {
        d = new Date(yy, mm - 1, dd + nNum);
    }
    else if (type == "m") {
        d = new Date(yy, mm - 1 + nNum, dd);
    }
    else if (type == "y") {
        d = new Date(yy + nNum, mm - 1, dd);
    }
    else if (type == "h") {
        d = new Date(yy , mm - 1, dd, hh + nNum);
    }

    yy = d.getFullYear();
    mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
    dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;
    hh = d.getHours(); hh = (hh < 10) ? '0' + hh : hh;

    if(type == "h") return '' + yy + '-' +  mm  + '-' + dd + ' '+ hh + ":00:00";
    return '' + yy + '-' +  mm  + '-' + dd;
}

var validateCalendar = function(){
	var start = new Date($("#searchStartDate").val());
	var end = new Date($("#searchEndDate").val());
	var limit = new Date($("#searchEndDate").val());
	console.log(end.getDate());
	limit.setDate(end.getDate()-30);
	if(start > end){
		alert("검색 시작일은 검색 종료일보다 뒤의 날짜를 선택할 수 없습니다.");
		return false;
	}else if(start < limit){
		console.log(start+","+limit);
		alert("검색 기간은 31일 간만 가능합니다.");
		return false;
	}
	return true;
}

function bytesToSize(bytes) {
	   var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
	   if (bytes == 0) return '0 Byte';
	   var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
	   return parseFloat(Math.round(bytes / Math.pow(1024, Math.floor(i)), 2)) + ' ' + sizes[i];


}

/**
 * Aircode Utility
 */
var addReadOnlyToSelectBox = function(id,obj){
	$('#'+id).prop('readonly', true).addClass("readonly");
	$('#'+id).click(function(){
		this.selectedValue = $(this).val();
	});
	$('#'+id).change(function(){
		$(this).val(this.selectedValue);
	});
};

var removeReadOnlyToSelectBox = function(id){
	$('#'+id).prop('readonly', false).removeClass("readonly");
	$('#'+id).off("click").off("change");
};

var getDigitNumber = function(value, digit){
	var input = value.toString();
	var output = "";
	for(var i=input.length-1;i>=0;i--){
		output = input[i]+output;
		if((input.length-i)%digit == 0 && i > 0 ){
			output = ","+output;
		}
	}
	return output
};

$(document).ready(function(){

	// 숫자입력
	$(".onlyNum").on("keyup blur", function(){
		var $this = $(this);
		$this.val($this.val().replace(/[^0-9\\-]/gi, ""));
	});

	// 숫자와 .입력
	$(".onlyNumDot").on("keyup blur", function(){
		this.value = this.value.replace(/[^\d.]/ig, '');
	});

//	$(".placeHolding").textPlaceholder();
//	$(window).bind("resize", FnPageLoaddingPosition );
//	$(window).bind("scroll", FnPageLoaddingPosition );

});
