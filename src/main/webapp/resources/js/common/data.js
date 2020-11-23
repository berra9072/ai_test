/**
 *
 */

var goPage = function(pageNo) {
	var param = {};
	getList(param, pageNo)
};

var getList = function(param, pageNo) {

	$("#pageCurIndex").val(pageNo);
	param.pageCurIndex = pageNo;

	showSpinner();
	$.ajax({
		url : adminUrl+getListUrl,
    	data : param,
    	type : "post",
    	dataType : "json",
        success:function(obj){
        	if(obj.result){
        		if(obj.data.length == 0 && pageNo >1){
        			goPage(pageNo-1);
        		}
        	}
        	draw(obj);
        },beforeSend : function(xmlHttpRequest){
        	xmlHttpRequest.setRequestHeader("AJAX", "true");
        }
		,error:function(x, e){
        	ajaxErrorChk(x, e);
        	drawEmptyList();
        }
        ,complete:function(){
        	hideSpinner();
        	if(isAwsSite){
        		$(".ppId").show();
        	}
        }
	});
	return false;
};

var saveData = function(){
	showSpinner();
	$("#popupForm").ajaxForm({
    	url: adminUrl+saveUrl,
    	type: "post",
    	dataType : "json",
    	data : {},
    	success : function(obj) {
    		if(obj.data == null) {
        		aleter("예외가 발생하였습니다.");
        		return false;
        	}
			alert(obj.data);
			if(obj.result){
				$("#close").trigger("click");
				var pageNo = $("#pageCurIndex").val() || "1";
				goPage(pageNo);
			}
		},
		beforeSend : function(xmlHttpRequest){
        	xmlHttpRequest.setRequestHeader("AJAX", "true");
        },
        error:function(x, e){
        	ajaxErrorChk(x, e);
        },
        complete:function(){
        	hideSpinner();
        }
    }).submit();
};

var deleteData = function(url, param){
	showSpinner();
	$.ajax({
		url : adminUrl + url
    	,data : param
    	,type : "post"
    	,dataType : "json"
        ,success:function(obj){
        	if(obj.data == null) {
        		aleter("예외가 발생하였습니다.");
        		return false;
        	}
        	alert(obj.data);
        	if (obj.result) {
        		var pageNo = $("#pageCurIndex").val() || "1";
	        	goPage(pageNo);
        	}
        },beforeSend : function(xmlHttpRequest){
        	xmlHttpRequest.setRequestHeader("AJAX", "true");
        }
		,error:function(x, e){
        	ajaxErrorChk(x, e);
        }
        , complete:function(){
        	hideSpinner();
        }
	});
};

var ajaxCommand = function(url, param){
	showSpinner();
	$.ajax({
		url : adminUrl + url
    	,data : param
    	,type : "post"
    	,dataType : "json"
        ,success:function(obj){
        	if(obj.data == null) {
        		aleter("예외가 발생하였습니다.");
        		return false;
        	}
        	alert(obj.data);

        },beforeSend : function(xmlHttpRequest){
        	xmlHttpRequest.setRequestHeader("AJAX", "true");
        }
		,error:function(x, e){
        	ajaxErrorChk(x, e);
        }
        , complete:function(){
        	hideSpinner();
        }
	});
};

var draw = function(obj){
	if(!obj.result){
		alert(obj.data);
	}

	if(obj.result && obj.data.length > 0){
		drawList(obj.data);
	}else{
		drawEmptyList();
	}

	// paging
	if(obj.paging != null){
		$('#paging').html(obj.paging);
	}
};

var drawList = function(data) {
	var $container = $("#ajaxTbl");
	$container.html(_.template($("#rows").html())({rows:data}));

};

var drawEmptyList = function(){
	var $container = $("#ajaxTbl");
	var html = ' <tr>'
	 	+  ' <td colspan="'
	 	+ (isAwsSite? emptyColASize : emptyColBSize)
	 	+'" class="noline tc">'+noDataMsg+'</td>'
	 	+  ' </tr>';
	$container.html(html);
};

var clear = function() {
	$("#popupForm")
	.find("input[type=text]").val("")
	.end().find("input[type=radio]").attr("checked", false)
	.end().find("input[type=hidden]").val("")
	.end().find("select").val("0")
	;
};




var goDataPage = function(pageNo) {
	var param = {};
	getDataList(param, pageNo)
}

var getDataList = function(param, pageNo) {
	$("#dataPageCurIndex").val(pageNo);
	param.pageCurIndex = pageNo;

	showSpinner();
	$.ajax({
		url : adminUrl+getDataListUrl,
		data : param,
		type : "post",
		dataType : "json",
		success:function(obj){
			if(obj.result){
				if(obj.data.length == 0 && pageNo >1){
					goDataPage(pageNo-1);
				}
			}
			dataDraw(obj);
		},beforeSend : function(xmlHttpRequest){
			xmlHttpRequest.setRequestHeader("AJAX", "true");
		}
		,error:function(x, e){
			ajaxErrorChk(x, e);
			drawEmptyList();
		}
		,complete:function(){
			hideSpinner();
			if(isAwsSite){
				$(".ppId").show();
			}
		}
	});
	return false;
};

var dataDraw = function(obj){
	if(!obj.result){
		alert(obj.data);
	}

	if(obj.result && obj.data.length > 0){
		dataDrawList(obj.data);
	}else{
		dataDrawEmptyList();
	}

	// paging
	if(obj.paging != null){
		$('#dataPaging').html(obj.paging);
	}
}

var dataDrawList = function(data) {
	var $container = $("#ajaxTbl2");
	$container.html(_.template($("#dataListRows").html())({rows:data}));
};

var dataDrawEmptyList = function(){
	var $container = $("#ajaxTbl2");
	var html = ' <tr>'
	 	+  ' <td colspan="'
	 	+ (isAwsSite? emptyColASize : emptyColBSize)
	 	+'" class="noline tc">'+noDataMsg+'</td>'
	 	+  ' </tr>';
	$container.html(html);
};