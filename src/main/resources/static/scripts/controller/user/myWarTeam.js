define(function(require, exports, module) {
    seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {
        var troopsId;
        var troopsId2;
        var captainId;
        var captainId2;
        var memberNew;
        $(function(){
            seniorLoad("teamInfoArea");
            $("#backTeam").click(function() {
                backTeam();
            });
            $("#backTeam2").click(function() {
                backTeam2();
            });
            $("ul.tabs li").click(function() {
                $("ul.tabs li").removeClass("active");
                $(this).addClass("active");
                $(".tab_content").hide();
                var activeTab = $(this).find("a").attr("href");
                $(activeTab).fadeIn();
                if("#tab1" == activeTab){
                    seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId);
                }else if("#tab2" == activeTab){
                    seniorLoad3("tabNo2","teamMember","pageContainer2",-1,"/user/myWarTeam/teamMemberList",troopsId);
                }else if("#tab3" == activeTab){
                    seniorLoad3("tabNo3","teamHistory","pageContainer3",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId);
                }else if("#tab4" == activeTab){
                    seniorLoad3("tabNo4","teamHonorjd","pageContainer4",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId2);
                }else if("#tab5" == activeTab){
                    seniorLoad3("tabNo5","teamMemberjd","pageContainer5",-1,"/user/myWarTeam/teamMemberList",troopsId2);
                }else if("#tab6" == activeTab){
                    seniorLoad3("tabNo6","teamHistoryjd","pageContainer6",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId2);
                }
                return false;
            });
            $(".playapply").click(function() {
                poprdsq();
            });
            $("#updateImg").on("change",function(){
                var file=$("#updateImg").val();
                if(file=="" || file ==null){
                    return;
                }
                submitFile();
            });
            function submitFile(){
                var formData = new FormData($("#form-add")[0]);
                $.ajax({
                    //接口地址
                    url: '/user/addTeamImg' ,
                    type: 'POST',
                    data: formData,
                    async: false,
                    cache: false,
                    contentType: false,
                    processData: false,
                    success: function (data) {
                        //成功的回调
                        if(data.code == 200){
                            $("#showImg").css("background-image","url('http://p22o6bknk.bkt.clouddn.com/"+data.msg+"')");
                            $("#img").val('http://p22o6bknk.bkt.clouddn.com/'+data.msg);
                        }else{
                            layer.msg("图片上传失败,请重试："+data.msg);
                        }
                    },
                    error: function (returndata) {
                        //请求异常的回调
                        alert("网络访问失败，请稍后重试!");
                    }
                });
            };
            function showImages(file,preview){
                var reader  = new FileReader();
                reader.onloadend = function () {
                    preview.attr("src",reader.result);
                }
                if (file) {
                    reader.readAsDataURL(file);
                } else {
                    preview.src="" ;
                }

            }
            //弹框
            $(".table_head  .left label").click(function(){
                var currency=$(this).attr("name");
                var className=$(this).attr("class");
                if("active"!=className ){

                    $("#teamInfo").show();
                    $(".historyTable").hide();
                    $(".playapply").show();
                    if("wz" ==currency){
                        $("#wz").show();
                        $("#jd").hide();
                    	$("#teamInfo").show();
                        $("#teamInfo2").hide();
                        seniorLoad("teamInfoArea");
                    }else if("jd"==currency){
                        $("#wz").hide();
                        $("#jd").show();
                        $("#teamInfo").hide();
                        $("#teamInfo2").show();
                        seniorLoadJd("teamInfoArea2");
                    }
                    var ind=$(this).index();
                    $(this).addClass("active").siblings().removeClass("active");
                    //$(".historyTable .table_box ").eq(ind).addClass("show").siblings().removeClass("show");
                }
            })
        });
        var isShow=true;
        var isShow2=true;
        var isShow3=true;
        var isShow4=true;
        var isShowjd=true;
        function backTeam() {
            var data={
                "troopsId":troopsId,
            };
            //退出战队
            $.ajax({
                url : "/user/myWarTeam/backTeam",
                datatype : 'json',
                type : "get",
                data : data,
                success : function(json) {
                    var data = json.code;
                    $(".exitzd").hide();
                    if("0"==data){
                        location.reload();
                    }else{
                        layer.msg(json.msg);
                    }

                }
            });
        }
        function backTeam2() {
            var data={
                "troopsId":troopsId2,
            };
            //退出战队
            $.ajax({
                url : "/user/myWarTeam/backTeam",
                datatype : 'json',
                type : "get",
                data : data,
                success : function(json) {
                    var data = json.code;
                    $(".exitzd").hide();
                    if("0"==data){
                        location.reload();
                    }else{
                        layer.msg(json.msg);
                    }

                }
            });
        }
        //获取列表数据
        function seniorLoad(tempId){
            if(isShow){
                isShow=false;
                $("#teamInfo").paginator({
                    itemTemplateId : tempId,
                    usepager:false,
                    useSeniorTemplate:true,
                    ajaxFuc : function(curentPage, renderHtml) {
                        var data={
                            "offset" : curentPage,
                            "limit" : 5
                        };

                        $.ajax({
                            url : "/user/myWarTeam/list",
                            datatype : 'json',
                            type : "get",
                            data : data,
                            success : function(json) {
                                var data = json.t;
                                var paramObj = {
                                    total : data.total,
                                    page : data.offset,
                                    list : data.troops
                                };
                                $(".mdat").html(data.newMsgCount);
                                var p = {
                                    troopsId : data.troops[0].id
                                };
                                $.ajax({
                                    url : "/user/myWarTeam/newMemberlist",
                                    datatype : 'json',
                                    type : "get",
                                    data : p,
                                    success : function(json) {
                                        var d = json.t;
                                        memberNew=d.result.length;
                                        $(".news").html(memberNew);
                                    }
                                });
                                troopsId=data.troops[0].id;
                                troopsId2=data.troops[1].id;
                                $("#tid").val(troopsId);
                                $("#tid2").val(troopsId2);
                                captainId =data.troops[0].captainId;
                                captainId2 =data.troops[1].captainId;
                                seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId);
                                renderHtml(data);
                                isShow=true;
                            }
                        });
                    }
                });
            }else{
                //alert("你点的太快了");
            }
        }
        function seniorLoadJd(tempId){
            if(isShowjd){
                isShowjd=false;
                $("#teamInfo2").paginator({
                    itemTemplateId : tempId,
                    usepager:false,
                    useSeniorTemplate:true,
                    ajaxFuc : function(curentPage, renderHtml) {
                        var data={
                            "offset" : curentPage,
                            "limit" : 5
                        };

                        $.ajax({
                            url : "/user/myWarTeam/list",
                            datatype : 'json',
                            type : "get",
                            data : data,
                            success : function(json) {
                                var data = json.t;
                                var paramObj = {
                                    total : data.total,
                                    page : data.offset,
                                    list : data.troops
                                };
                                $(".mdat").html(data.newMsgCount);
                                var p = {
                                    troopsId : data.troops[0].id
                                };
                                $.ajax({
                                    url : "/user/myWarTeam/newMemberlist",
                                    datatype : 'json',
                                    type : "get",
                                    data : p,
                                    success : function(json) {
                                        var d = json.t;
                                        memberNew=d.result.length;
                                        $(".news").html(memberNew);
                                    }
                                });
                                troopsId=data.troops[0].id;
                                $("#tid").val(troopsId);
                                captainId =data.troops[0].captainId;
                                captainId2 =data.troops[1].captainId;
                                seniorLoad3("tabNo1","teamHonor","pageContainer1",-1,"/user/myWarTeam/teamHonorOrHistoryList",troopsId);
                                renderHtml(data);
                                isShowjd=true;
                            }
                        });
                    }
                });
            }else{
                //alert("你点的太快了");
            }
        }
        function seniorLoad3(tabNo1,tempId,page,type,url,tid){
            if(isShow3){
                isShow3=false;
                $("#"+tabNo1).paginator({
                    itemTemplateId : tempId,
                    pageNavId : page,
                    usepager:true,
                    useSeniorTemplate:true,
                    ajaxFuc : function(curentPage, renderHtml) {
                        var data={
                            "offset" : curentPage,
                            "limit" : 5,
                            "troopsId":tid,
                            "operation":type
                        };

                        $.ajax({
                            url : url,
                            datatype : 'json',
                            type : "get",
                            data : data,
                            success : function(json) {
                                var data = json.t;
                                var paramObj = {
                                    total : data.total,
                                    page : data.offset,
                                    list : data.result
                                };
                                if("tabNo2"==tabNo1 || "tabNo5" ==tabNo1){
                                    if("tabNo2"==tabNo1){
                                        data.page["captainId"] = captainId;
                                    }else if("tabNo5"==tabNo1){
                                        data.page["captainId"] = captainId2;
                                    }
                                    renderHtml(data.page);
                                }else{
                                    renderHtml(data);
                                }
                                isShow3=true;
                            }
                        });
                    }
                });
            }else{
                //alert("你点的太快了");
            }
        }
        function poprdsq() {
            isShow4=true;
            if(isShow4){
                isShow4=false;
                $("#newMemberlist").paginator({
                    itemTemplateId : "teamnNewMember",
                    usepager:false,
                    useSeniorTemplate:true,
                    ajaxFuc : function(curentPage, renderHtml) {
                        var p2 = {
                            troopsId : troopsId
                        };
                        $.ajax({
                            url : "/user/myWarTeam/newMemberlist",
                            datatype : 'json',
                            type : "get",
                            data : p2,
                            success : function(json) {
                                var data = json.t;
                                renderHtml(data);
                                isShow4=true;
                                $(".poprdsq").fadeIn();
                            }
                        });
                    }
                });
            }
        }
    });
});
