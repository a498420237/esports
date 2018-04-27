(function(factory) {
	if (typeof define === 'function') {
		// 如果define已被定义，模块化代码
		define('pagePlugin', [ 'jquery', 'utilService','bootstrap','bootstrapPaginator','template'],
				function(require, exports, moudles) {
					factory(require('jquery'),require('utilService'),require('bootstrap'),require('bootstrapPaginator'),require('template')); // 初始化插件
					return jQuery; // 返回jQuery
				});
	} else {
		// 如果define没有被定义，正常执行jQuery
		factory(jQuery);
	}
}(function($, util,bootstrap,paginator,template) {
	var paginator = function(ele, opt) {
		this.defaults = {
			itemTemplateId : '', // 条目模版Id
			pageNavId : '', // 分页导航id
			ajaxFuc : null, // 获取数据
			ele : ele,
			usepager:true,
			useSeniorTemplate:false,
		};
		this.settings = $.extend({}, this.defaults, opt);
	};
	paginator.prototype={
		init:function(){
			this.getPageData(this,1);
		},
		getPageData:function(obj,currentPage){
			this.settings.ajaxFuc(currentPage,function(data){
				var controlHtml='';
				if(obj.settings.useSeniorTemplate){
					controlHtml=template(obj.settings.itemTemplateId, data);
				}else{
					var itemHtml = $("#"+obj.settings.itemTemplateId).val();
	  				data.list.forEach(function(obj) {
	  					controlHtml += itemHtml.repalceVars(obj);
	      			});
				}
  				obj.settings.ele.html(controlHtml);
  				if(obj.settings.usepager){
	                var total = data.total; //取到pageCount的值(把返回数据转成object类型)
	                var currentPage = data.page; //得到urrentPage
	  	            if (currentPage && total) {
	  	            	obj.render(obj,currentPage, total);
	  	            }
  				}
			});
			
		},
		render:function(obj,currentPage,totalPages){
			var options={
				bootstrapMajorVersion: 3, //版本
                currentPage: currentPage,
                totalPages: totalPages,
                size:"small",
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                },
                tooltipTitles: function (type, page, current) {
                	// 如果想要去掉页码数字上面的预览功能，则在此操作。例如：可以直接return。
                	switch (type) {
                	case "first":
                		return "跳转到首页";
                	case "prev":
                		return "跳转到前一页";
                	case "next":
                		return "跳转到后一页";
                	case "last":
                		return "跳转到尾页";
                	case "page":
                		return (page === current) ? "当前页面是 " + page : "转到页面 "
                				+ page;
                	}
                },
                shouldShowPage: function (type, page, current) {
                    var result = true;
                    switch (type) {
                        case "first":
                            result = false;
                            break;
                        case "prev":
                            result =true;
                            break;
                        case "next":
                            result = true;
                            break;
                        case "last":
                            result = false;
                            break;
                        case "page":
                            result = true;
                            break;
                    }
                    return result;
                },
                onPageClicked: function (event, originalEvent, type, page) {
                    // show the corresponding page and retrieve the newly built item related to the page clicked before for the event return
                    var currentTarget = $(event.currentTarget);

                    switch (type) {
                        case "first":
                            currentTarget.bootstrapPaginator("showFirst");
                            obj.getPageData(obj,page);
                            break;
                        case "prev":
                            currentTarget.bootstrapPaginator("showPrevious");
                            obj.getPageData(obj,page);
                            break;
                        case "next":
                            currentTarget.bootstrapPaginator("showNext");
                            obj.getPageData(obj,page);
                            break;
                        case "last":
                            currentTarget.bootstrapPaginator("showLast");
                            obj.getPageData(obj,page);
                            break;
                        case "page":
                            currentTarget.bootstrapPaginator("show", page);
                            obj.getPageData(obj,page);
                            break;
                    }
                }
			};
			if(obj.settings.usepager){
				$('#'+obj.settings.pageNavId).bootstrapPaginator(options);
			}
		}
	};
	$.fn.paginator=function(options){
		var _paginator=new paginator(this,options);
		return this.each(function(){
			_paginator.init();
		});	
	};
}));