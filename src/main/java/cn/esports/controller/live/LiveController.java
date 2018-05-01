package cn.esports.controller.live;

import cn.esports.controller.BaseController;
import cn.esports.service.LiveService;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="huangxiaofeng@wxchina.com">XiaoFeng Huang</a>
 * @version 1.0.0
 * @description: 直播
 * @date 2018/4/27
 */
@RestController
public class LiveController extends BaseController {

    @Autowired
    private LiveService liveService;

    @RequestMapping(value = "/live/index")
    public ModelAndView live(){
        ModelAndView view =new ModelAndView("live/index");
        return view;
    }

    @RequestMapping(value = "live/liveTitle")
    public JSONObject getGameList(@RequestParam Map<String, String> uriVariables){
        JSONObject gameList = liveService.getGameList(uriVariables);
        return gameList;
    }

    @RequestMapping(value = "/live/list")
    public JSONObject getLiveList(@RequestParam Map<String, String> uriVariables){
        JSONObject liveList = liveService.getList(uriVariables);
        return liveList;
    }

    @RequestMapping(value = "/live/recommendLive")
    public JSONObject getRecommandLive(@RequestParam Map<String, String> uriVariables){
        JSONObject recommendLive = liveService.getRecommendLive(uriVariables);
        return recommendLive;
    }


    @RequestMapping(value = "/live/detail/{liveId}", method = RequestMethod.GET)
    public ModelAndView liveDetailbyId(@PathVariable Long liveId) throws IOException {
        ModelAndView view = new ModelAndView("live/detail");
        JSONObject result = liveService.liveDetailbyId(liveId);
        Object recommendLive = result.get("t");
        view.addObject("liveInfo", ((Map) recommendLive).get("liveInfo"));

        Object handicapListInfo = ((Map)((Map) recommendLive).get("liveInfo")).get("handicapListInfo");
        view.addObject("handicapListInfo", handicapListInfo);
        return view;
    }

}
