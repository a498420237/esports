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
	exports.bb=function(bb){
		alert(bb);
	};
});