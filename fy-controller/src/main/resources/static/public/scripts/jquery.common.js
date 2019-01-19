/*
 * jQuery JavaScript Extend Library Core v1.3.2
 * http://jvones.com/
 *
 * Copyright (c) 2009 Jvones
 * Dual licensed under the MIT and GPL licenses.
 * http://docs.jquery.com/License
 *
 * Date: 2009-10-30 17:34:21 -0500 
 * Revision: 6246
 */
//当前浏览器
var userAgent = navigator.userAgent.toLowerCase();
var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
var is_moz = (navigator.product == 'Gecko') && userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera) && userAgent.substr(userAgent.indexOf('msie') + 5, 3);


/**
 * jquery 扩展函数
 */
jQuery.extend({
  getCookie : function(sName) {
    var aCookie = document.cookie.split("; ");
    for (var i=0; i < aCookie.length; i++){
      var aCrumb = aCookie[i].split("=");
      if (sName == aCrumb[0]) return decodeURIComponent(aCrumb[1]);
    }
    return '';
  },
  setCookie : function(sName, sValue, sExpires) {
    var sCookie = sName + "=" + encodeURIComponent(sValue);
    if (sExpires != null) sCookie += "; expires=" + sExpires;
    document.cookie = sCookie;
  },
  removeCookie : function(sName) {
    document.cookie = sName + "=; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
  },
  
  /**
   * 验证email格式
   */
  checkemail : function(email) {
	  var emailrule = new RegExp("^([a-zA-Z0-9_-]+[.]*[a-zA-Z0-9_-]+)+@[a-zA-Z0-9_-]+([.][a-zA-Z0-9_-]+)+$", "ig");
	  if(emailrule.test(email)) {
		  return true;
	  } 
	  return false ;
   },

  /**
   * 验证密码格式
   */
  checkpassword : function(password) {
	  var pswrule = new RegExp("[a-zA-Z0-9_-]{2,15}");
	  if(!pswrule.test(password)) {
		  return false;
	  }
	  return true;
  }
  
});


//全选插件
//checkall_name   操作全选的复选框name
//checkbox_name   被操作的复选框的name  name值可不统一 设置包含值 以XXX开头 自己修改
(function ($) {
	$.fn.check = function (options) {
		var defaults = {
				checkall_name: "checkall_name", //全选框name
				checkbox_name: "checkbox_name" //被操作的复选框name
			},
			ops = $.extend(defaults, options);
		e = $("input[name='" + ops.checkall_name + "']"), //全选
			f = $("input[name='" + ops.checkbox_name + "']"), //单选
			g = f.length; //被操作的复选框数量
		f.click(function () {
			$("input[name ='" + ops.checkbox_name + "']:checked").length == $("input[name='" + ops.checkbox_name + "']").length ? e.attr("checked", !0) : e.attr("checked", !1);
		}), e.click(function () {
			for (i = 0; g > i; i++) f[i].checked = this.checked;
		});
	};
})(jQuery);

/* 默认选中 */
function onchecked(id){
	if ($("#ckid"+id).hasClass('checked')) {
			$("#ckid"+id).removeClass('checked');
			$("#ckid"+id).find('input[type="checkbox"]').removeAttr('checked');
		} else {
			$("#ckid"+id).addClass('checked');
			$("#ckid"+id).find('input[type="checkbox"]').attr('checked','checked');
		}
}

function isValue(param) {
	if (null == param || '' == param || "" == param || "undefined" == param)
		return false;
	else 
		return true;
}

function isNull(params) {
	for (var i = 0, len = params.length; i < len; i++) {
		if (null == params[i] || "" == params[i] || "undefined" == params[i])
			return false;
		else 
			return true;
	}
}


//字符串转日期型
function parseDate(str) {
    if (str instanceof Date) {
        return str;
    }
    if (typeof str == 'string') {
    var results = str.match(/^ *(\d{4})(\d{2})(\d{2}) *$/);
    if (results && results.length > 3)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10));
    results = str.match(/^ *(\d{4})(\d{2})(\d{2})(\d{2})(\d{2})(\d{2}) *$/);
    if (results && results.length > 6)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10), parseInt(results[4],10), parseInt(results[5],10), parseInt(results[6],10));
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);
    if (results && results.length > 3)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10));
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}) *$/);
    if (results && results.length > 5)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10), parseInt(results[4],10), parseInt(results[5],10));
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);
    if (results && results.length > 6)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10), parseInt(results[4],10), parseInt(results[5],10), parseInt(results[6],10));
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);
    if (results && results.length > 7)
        return new Date(parseInt(results[1],10), parseInt(results[2],10) - 1, parseInt(results[3],10), parseInt(results[4],10), parseInt(results[5],10), parseInt(results[6],10), parseInt(results[7],10));
    }
    return null;
}

//计算时间差函数
function TimeDiffirence(startTime,endTime){
    var diffDetail = {};
    
    try{
	    var date1 = getObjectType(startTime) == "date" ? startTime : parseDate(startTime);
	    var date2 = getObjectType(endTime) == "date" ? endTime : parseDate(endTime);
	    //毫秒差
	    diffDetail.msDiff = date2.getTime()-date1.getTime();
	    //分钟差
	    diffDetail.minutesDiff=Math.floor(diffDetail.msDiff/(60*1000));
	    //小时差
	    diffDetail.hoursDiff=Math.floor(diffDetail.minutesDiff/60);
	    //天数差
	    diffDetail.daysDiff=Math.floor(diffDetail.hoursDiff/24);
	    
	}catch(e){}
		
	return diffDetail;
}

//判断变量类型
function getObjectType(o) {
    var _t;
    return ((_t = typeof(o)) == "object" ? Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
}

function getSign(params, url) {
    var timestamp = new Date().getTime();//每次请求都是新的时间戳
    params['timestamp'] = timestamp;
    params['language'] = "zh_CN";

    var mapArr = [];
    for (var key in params) {
        mapArr.push(key);
    }
    mapArr.sort();
    var sign = "";
    $.each(mapArr, function (i, key) {
        sign = sign + key + "=" + params[key] + "&";
    });
    sign = sign.substring(0, sign.length - 1);
    sign = $.md5(sign).toString();

    params['sign'] = sign;
    params['signType'] = "MD5";
    return params;
}