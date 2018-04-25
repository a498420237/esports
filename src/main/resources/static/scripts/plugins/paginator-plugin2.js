define(function(require,exports,moudles){
     return function(jquery){
         (function($) {
        	 seajs.use('utilService');
        	 seajs.use('bootstrapPaginator');
        	 var paginator=function(ele, opt){
      			this.defaults ={
      				itemTemplateId:'',  //条目模版Id
      				pageNavId:'',    //分页导航id
      				ajaxFuc:null,    //获取数据
      				ele:ele    
      			};
      			this.settings = $.extend({}, this.defaults, opt);
      		};
      		
      		paginator.prototype={
      			init:function(){
      				this.getPageData(this,1);
      			},
      			getPageData:function(obj,currentPage){
      				this.settings.ajaxFuc(function(data){
      					var controlHtml='';
          				var itemHtml = $("#"+obj.settings.itemTemplateId).val();
          				data.list.forEach(function(obj) {
          					controlHtml += itemHtml.repalceVars(obj);
              			});
          				obj.settings.ele.html(controlHtml);
                        var total = data.total; //取到pageCount的值(把返回数据转成object类型)
                        var currentPage = data.page; //得到urrentPage
          	            if (currentPage && total) {
          	            	obj.render(obj,currentPage, total);
          	            }
      				});
      				
      			},
      			render:function(obj,currentPage,totalPages){
      				var options={
      	                currentPage: currentPage,
      	                totalPages: totalPages,
      	                size:"small",
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
      	                            this.getPageData(page)
      	                            break;
      	                        case "prev":
      	                            currentTarget.bootstrapPaginator("showPrevious");
      	                            _mms.paginator.getPageData(page)
      	                            break;
      	                        case "next":
      	                            currentTarget.bootstrapPaginator("showNext");
      	                            _mms.paginator.getPageData(page)
      	                            break;
      	                        case "last":
      	                            currentTarget.bootstrapPaginator("showLast");
      	                            _mms.paginator.getPageData(page)
      	                            break;
      	                        case "page":
      	                            currentTarget.bootstrapPaginator("show", page);
      	                            _mms.paginator.getPageData(page)
      	                            break;
      	                    }
      	                }
      				};
      				
      		        $('#'+obj.settings.pageNavId).bootstrapPaginator(options);
      			}
      		};
      		
      		$.fn.paginator=function(options){
      			var _paginator=new paginator(this,options);
      			return this.each(function(){
      				_paginator.init();
      			});	
      		};
         })(jquery);
     }
});

