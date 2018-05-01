define(function(require, exports, module) {



    seajs.use([ 'jquery', 'pagePlugin', 'utilService','template'], function($,pagePlugin, util,template) {

        var titleDesc = {
            xx : {
                "关于我们": "About US",
                "媒体报道": "Media Report",
                "投资方&顾问": "Investors & Consultants",
                "工作机会": "Job Opportunities",
                "网站条款": "Website Terms",
                "新手指南": "Beginner's Guide",
                "公众号": "Official Accounts"
            }
        };


        $(".content li").click(function () {
            $('.content li').removeClass("active");
            $(this).addClass("active");
            $(".rt .head p").text(titleDesc.xx[$(this).text()]);
            $("#title").text($(this).text());
        });
    });
});
