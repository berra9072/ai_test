if (typeof(comcode) == "undefined") comcode = {};

var GETCODE = {
	"com":{
		group:"codeGroup",
		uri:'/com/comCode',
		field: {
			code: 'itemCode', //코드칼럼명
			name: 'itemName'  //명칭칼럼명
		}
	}
};

comcode.Code = {
	get: function(attr) {

		if (!attr.field) {
			attr.field = {
				code: 'itemCode', //코드칼럼명
				name: 'itemName'  //명칭칼럼명
			};
		}
		for (var id in this.generate) {
			if (id == attr.group) {
				return this.generate[id](attr);
			}
		}
		return [];
	},
	generate: {
		//년도
		YEAR: function(attr) {
			var ret = [];
			var curYear = new Date().getFullYear();
			var maxYear = curYear;
			var minYear = 2015;

			if(attr.max)	maxYear = curYear + attr.max;
			if(attr.min)	minYear = curYear + attr.min;

			for(var i = minYear ; i <= maxYear ; i++) {
				ret[ret.length] = {itemCode:i, itemName:i+'년'};
			}
			return ret;
		},
		//월
		MONTH: function() {
			var ret = [];
			for(var i = 1 ; i <= 12 ; i++){
				var v = (i < 10 ? '0'+i : ''+i);
				ret[ret.length] = {itemCode:v, itemName:v+'월'};
			}
			return ret;
		},
		//일
		DAY: function() {
			var ret = [];
			for(var i = 1 ; i <= 31 ; i++){
				var v = (i < 10 ? '0'+i : ''+i);
				ret[ret.length] = {itemCode:v, itemName:v+'일'};
			}
			return ret;
		},
		//일
		DAYLIST: function(attr) {
			var ret = [];
			for(var i = 1 ; i <= attr.dayparam ; i++){
				var v = (i < 10 ? '0'+i : ''+i);
				ret[ret.length] = {itemCode:v, itemName:v+'일'};
			}
			return ret;
		},
		//시간
		HOUR: function() {
			var ret = [];
			for(var i = 0 ; i <= 23 ; i++){
				var v = (i < 10 ? '0'+i : ''+i);
				ret[ret.length] = {itemCode:v, itemName:v};
			}
			return ret;
		},
		//시간
		MINUTE: function() {
			var ret = [];
			for(var i = 0 ; i <= 59 ; i++){
				var v = (i < 10 ? '0'+i : ''+i);
				ret[ret.length] = {itemCode:v, itemName:v};
			}
			return ret;
		}
	}
};

comcode.Store = {
	load : function(attr, callback) {
	  	if (attr.type=='getday') {
			var data = comcode.Code.get(attr);
			callback(attr, data);
	  		return;
	  	}

	  	this.init(attr);

		$.ajax({
			url: attr.uri,
			data: attr.args,
			type: 'POST',
			success: function(data) {
				callback(attr, data);
			}
		});
	},
	init: function(attr) {
		if (!attr.target)
			attr.target = 'com';

		var conf = GETCODE[attr.target];

		var uri	 = conf.uri;
		var args = {};

		if (attr.param) {
			args[conf.group] = attr.group;
			args['param']    = $.toJSON(attr.param);
		} else {
			args[conf.group] = attr.group;
			args['sortname'] = attr.sortname;
		}
		var field = conf.field;
		if (attr.field) {
			if (attr.field.name) field.name = attr.field.name;
			if (attr.field.code) field.name = attr.field.code;
		}

		if (attr.codeParam) {
			args['codeParam']    = attr.codeParam;
		}

		attr.field = field;
		attr.args = args;
		attr.uri = uri;
	},
	list : function(attr) {
	  	if (attr.type=='getday')
	  		return comcode.Code.get(attr);

		var result = null;
	  	this.init(attr);
		$.ajax({
			url: attr.uri,
			data: attr.args,
			type: 'POST',
			async: false,
			success: function(data) {
				result = data;
			}
		});
		return result;
	},
	create: function(attr, data) {
		var s = "";
		for(var i in data) {
			if (s.length > 0)
				s += ";";
			s += data[i][attr.field.code]+":"+data[i][attr.field.name];
		}
		attr.sucess({value: s});
	},
	options : function(attr) {

		var result = this.list(attr);
		var items  = ['null:'];
  		$.each(result, function(i, v) {
  			items.push(result[i][attr.field.code]+":"+result[i][attr.field.name]);
  		});
  		if (result == null)
  			return null;
		return items.join(';');
	},
	select : function(attr) {
		var opt = this.options(attr);
		if (opt && attr.success)
			attr.success(opt);
	},
	//HTML 테이블로 코드를 구성
	html: function(attr) {
		var result = this.list(attr);
		var s = '';
		var j = 0;
		var c = attr.cols;

  		$.each(result, function(i, v) {
			j = i%c;
  			var cd = result[i][attr.field.code];
  			var tt = result[i][attr.field.name];

  			if (j==0)
  				s += '<tr>';
			s += '<th class="c"> '+tt+' </th>';
			s += '<td> <input type="checkbox" value="'+cd+'" name="'+attr.name+'"> </td>';
			if (j==(c-1))
				s += "</tr>";
  		});
  		var remain = result.length % c;
  		for(var n=0; n<(c-remain); n++) {
  			s += '<th>&nbsp;</th><td>&nbsp;</td>';
  		}
  		if (remain != 0) {
  			s += "</tr>";
  		}
  		if (attr.width)
  			$('#'+attr.table).attr('width',attr.width);
  		$('#'+attr.table).html(s);
	}
};

comcode.Combo = {
	//콤보박스옵션정의
	select : function(attr) {

		//초기항목만 정의
		if(attr.mode == 0) {
	    	this.write(attr);
	    	return;
		}

		comcode.Store.load(attr, function(attr, data) {
	    	comcode.Combo.write(attr, data);
	    	if(attr.fn) attr.fn();
		});
	},
	//콤보옵션생성
	create: function(attr, data) {
    	var s = "";
    	if (attr.init) {
    		if (attr.init=='space')			s = "<option value=''> </option>";
    		else if (attr.init=='select')	s = "<option value=''>== 선택 ==</option>";
    		else if (attr.init=='all')		s = "<option value=''>== 전체 ==</option>";
    		else			    			s = "<option value='"+attr.init.value+"'>"+attr.init.text+"</option>";
    	}
    	if (data && data.length > 0) {
	    	for(var i=0; i<data.length; i++) {
	    		var cd = data[i][attr.field.code];
	    		var nm = data[i][attr.field.name];
	    		switch(attr.mode) {
	    			case 1	:	s += "<option value='"+cd+"'>"+nm+"</option>";	break;
	    			case 2	:	s += "<option value='"+cd+"'>"+cd+"</option>";	break;
	    			case 3	:	s += "<option value='"+cd+"'>"+nm+" ["+cd+"]</option>";	break;
	    			default	:	s += "<option value='"+cd+"'>["+cd+"] "+nm+"</option>";	break;
	    		}
	    	}
    	}
    	return s;
	},
	//콤보HTML write
	write : function(attr, data) {

		var opt_str 	= this.create(attr, data);
		var cb_id		= (attr.id ? attr.id : attr.name);
		var cb_value	= attr.value;

		var obj = $('#'+cb_id);
		obj.html(opt_str);
		if(cb_value) {
			obj.val(cb_value);
		}

	}
};

comcode.CheckBox = {
	select : function(attr) {
		comcode.Store.load(attr, function(attr, data) {
	    	comcode.CheckBox.write(attr, data);
	    	if(attr.fn) attr.fn();
		});
	},
	checkAll: function(obj) {
	    var chk = (obj.checked ? 'checked' : '');
	    $('input:checkbox[name="'+obj.name+'"]').each(function(idx){
			if(chk){
				$('input:checkbox[name="'+obj.name+'"]')[idx].checked = true;
			}else{
				$('input:checkbox[name="'+obj.name+'"]')[idx].checked = false;
			}
		})

	},
	checkPub: function(obj) {
	    var chk = (obj.checked ? 'checked' : '');

	    $('input:checkbox[name="'+obj.name+'"]').each(function(){
			if(!this.checked){
				$('input:checkbox[name="'+obj.name+'"]')[0].checked = false;
			}
		});

	},
	//체크박스 생성
	create: function(attr, data) {
    	var s = "";

    	if (attr.init) {
    		if (attr.init=="all")
    			s += '<div style="float:left"><input type="checkbox" name="'+attr.name+'" id="'+attr.name+'All" value="ALL"/> <label for="'+attr.name+'All">전체</label></div> ';
    		else if (attr.init=="checkall")
    			s += '<div style="float:left"><input type="checkbox" name="'+attr.name+'" id="'+attr.name+'All" value="ALL" onclick="comcode.CheckBox.checkAll(this)"/> <label for="'+attr.name+'All">전체</label></div> ';
    		else
    			s += '<div style="float:left"><input type="checkbox" name="'+attr.name+'" id="'+attr.name+attr.init.value+'" value="'+attr.init.value+'"/> <label for="'+attr.name+attr.init.value+'">'+attr.init.text+'</label></div> ';
    	}
    	if (data && data.length > 0) {
	    	for(var i=0; i<data.length; i++) {
	    		var cd = data[i][attr.field.code];
	    		var nm = data[i][attr.field.name];

	    		if (attr.init=="checkall"){
	    			s += "<div style='float:left'><input type='checkbox' name='"+attr.name+"' id='"+attr.name+cd+"' value='"+cd+"' onclick='comcode.CheckBox.checkPub(this)'/> <label for='"+attr.name+cd+"'>"+nm+"</label></div> ";
	    		}else{
	    			s += "<div style='float:left'><input type='checkbox' name='"+attr.name+"' id='"+attr.name+cd+"' value='"+cd+"'/> <label for='"+attr.name+cd+"'>"+nm+"</label></div> ";
	    		}
	    	}
    	}
    	return s;
	},
	write : function(attr, data) {

		var opt_str 	= this.create(attr, data);
		var cb_name		= attr.name;
		var cb_value	= attr.value;

		$('#'+attr.id).html(opt_str);

		//배열 형태
		jQuery( '[name*="'+cb_name+'"]' ).each( function(index) {
			if (cb_value)
				$(this).attr("checked", "checked");
		});
	}
};

//attr.id (필수)
//attr.name (필수)
//attr.group (필수)
comcode.Radio = {
	//라디오옵션정의
	select : function(attr) {
		comcode.Store.load(attr, function(attr, data) {
	    	comcode.Radio.write(attr, data);
	    	if(attr.fn)
	    		attr.fn();
		});
	},
	//라디오 생성
	create: function(attr, data) {
  	var s = "";

  	if (attr.init) {
  		if (attr.init=="all")
  			s += '<div style="float:left"><input type="radio" name="'+attr.name+'" id="'+attr.name+'All" value="" checked="checked"/> <label for="'+attr.name+'All">전체</label> </div> ';
  		else
  			s += '<div style="float:left"><input type="radio" name="'+attr.name+'" id="'+attr.name+attr.init.value+'" value="'+attr.init.value+'"/> <label for="'+attr.name+attr.init.value+'">'+attr.init.text+'</label> </div> ';
  	}
  	if (data && data.length > 0) {
	    	for(var i=0; i<data.length; i++) {
	    		var cd = data[i][attr.field.code];
	    		var nm = data[i][attr.field.name];
	    		s += "<div style='float:left'><input type='radio' name="+attr.name+" id='"+attr.name+cd+"' value='"+cd+"'/> <label for='"+attr.name+cd+"'>"+nm+"</label> </div> ";
	    	}
  	}
  	return s;
	},
	write : function(attr, data) {

		var opt_str 	= this.create(attr, data);
		var cb_name		= attr.name;
		var cb_value	= attr.value;

		$('#'+attr.id).html(opt_str);

		$('input:radio[name="'+cb_name+'"]:input[value="'+cb_value+'"]').attr('checked',true);

	}
};
