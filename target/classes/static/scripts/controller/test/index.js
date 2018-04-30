define(function(require, exports, module) {
	seajs.use([ 'jquery','pagePlugin','utilService'], function($,pagePlugin,util) {
		$("#conttent").paginator({
			itemTemplateId:'itemTemplate',
			pageNavId:'pageContainer',
			ajaxFuc:function(curentPage,renderHtml){
				$.ajax({
                	url : "/list",
                    datatype: 'json',
                    type: "get",
                    data : {"page" : curentPage},
                    success: function (data) {
                    	var data={total:data.total,page:data.page,list:data.zxlist};
                    	renderHtml(data);
                    }
                });
			}
		});
	});
});