define(function(require, exports, module) {
	//替换模版变量
	String.prototype.repalceVars = function(obj) {
	    return this.replace(/\$\w+\$/gi, function(matchs) {
	        var returns = obj[matchs.replace(/\$/g, "")];		
	        return (returns + "") == "undefined"? "": returns;
	    });
	};
	String.prototype.aa = function(obj) {
	    alert(obj);
	};
	//日期原型  格式化方法    fmt 格式 如：yyyy-MM-dd
	Date.prototype.format = function (fmt) {
		var o = {    
				"M+": this.getMonth() + 1, //月份     
				"d+": this.getDate(), //日     
				"H+": this.getHours(), //小时     
				"m+": this.getMinutes(), //分     
				"s+": this.getSeconds(), //秒     
				"q+": Math.floor((this.getMonth() + 3) / 3), //季度     
				"S": this.getMilliseconds() //毫秒     
		};    
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));    
		for (var k in o)    
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));    
		return fmt;    
	};    
	
	
	exports.bb=function(bb){
		alert(bb);
	};
	
	//填充模板方法
	exports.fillHtml = function(domId, templateHtml, data){
		var content = "";
		data.forEach(function(obj) {
			content += templateHtml.repalceVars(obj);
		});
		$("#"+domId).html(content);
	}
});