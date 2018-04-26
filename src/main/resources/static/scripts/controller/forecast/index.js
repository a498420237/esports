define(function(require, exports, module) {
	seajs.use([ 'jquery', 'pagePlugin', 'utilService' ], function($,pagePlugin, util) {
		
		$("#list_content").paginator({
			itemTemplateId : 'itemTemplate',
			pageNavId : 'pageContainer',
			ajaxFuc : function(curentPage, renderHtml) {
				$.ajax({
					url : "/forecast/list",
					datatype : 'json',
					type : "get",
					data : {
						"offset" : curentPage,
						"limit" : 2
					},
					success : function(json) {
						var data = json.t;
						var paramObj = {
							total : data.total,
							page : data.page,
							list : data.result
						};
						renderHtml(paramObj);
					}
				});
			}
		});
	});
});
