package cn.esports.controller.live;

import cn.esports.controller.BaseController;
import cn.esports.service.LiveService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author <a href="huangxiaofeng@wxchina.com">XiaoFeng Huang</a>
 * @version 1.0.0
 * @description: 直播
 * @date 2018/4/27
 */
@RestController
@RequestMapping("/live")
public class LiveController extends BaseController {

    @Autowired
    private LiveService liveService;

    @RequestMapping(value = "/index")
    public ModelAndView live(){
        ModelAndView view =new ModelAndView("live/index");
        return view;
    }

    @RequestMapping(value = "/list")
    public JSONObject getList(@RequestParam Map<String, String> uriVariables){
        JSONObject xx = liveService.getList(uriVariables);
        return xx;
    }

    @RequestMapping(value = "/recommendLive")
    public JSONObject getRecommandLive(@RequestParam Map<String, String> uriVariables){
        JSONObject recommendLive = liveService.getRecommendLive(uriVariables);
        return recommendLive;
    }


    @RequestMapping(value = "/detail")
    public ModelAndView liveDetailbyId(){
        ModelAndView view = new ModelAndView("live/detail");
        return view;
    }

}
