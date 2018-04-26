define(function(require, exports, module) {
	seajs.use([ 'jquery','pagePlugin','utilService'], function($,pagePlugin,util) {
		var gameId=-1;
		$("#list_content").paginator({
			itemTemplateId:'itemTemplate',
			pageNavId:'pageContainer',
			ajaxFuc:function(curentPage,renderHtml){
				$.ajax({
                	url : "/forecast/list",
                    datatype: 'json',
                    type: "get",
                    data : {"pageindex" : curentPage,"pagesize":10,"gameId":gameId},
                    success: function (data) {
                    	var data={total:data.total,page:data.pageindex,list:data.resultlist};
                    	renderHtml(data);
                    }
                });
			}
		});
	});
});