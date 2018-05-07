package cn.esports.controller.free;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.entity.JsonResp;
import cn.esports.entity.LiveStreamRoom;
import cn.esports.entity.MatchInfo;
import cn.esports.entity.Troops;
import cn.esports.entity.base.RestEntity;
import cn.esports.entity.dto.LiveStreamDto;
import cn.esports.entity.MatchInfo.TBean.ResultBean;
import cn.esports.service.CompetitionService;
import cn.esports.service.CompetitionTroopsService;
import cn.esports.service.ForecastService;
import cn.esports.service.LiveStreamRoomService;
import cn.esports.service.MatchService;
import cn.esports.service.MessageService;

/**
 * 
* <p>Title: IndexController</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
@RestController
public class IndexController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	@Autowired
	private CompetitionTroopsService troopsService;
	@Autowired
	public ForecastService forecastService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private CompetitionService competitionService;
	
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	@Autowired
	private LiveStreamRoomService liveStreamRoomService;
	/**
	 * Banner列表
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/getbanner", method = RequestMethod.GET)
	public JSONObject getbanner(@RequestParam Map<String, String> uriVariables){
		JSONObject json= forecastService.getBanner(uriVariables);
		return json;
	}
	/**
	 * 赛事列表
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/getLatestMatch", method = RequestMethod.GET)
	public JSONObject getLatestMatch(@RequestParam Map<String, String> uriVariables){
		JSONObject json= competitionService.getCompetitions(uriVariables);
		return json;
	}
	
	/**
	 * 获取主页资讯列表
	 * @return
	 */
	@RequestMapping(value = "/getLatestContentInfo", method = RequestMethod.GET)
	public JSONObject getLatestContentInfo(@RequestParam Map<String, String> uriVariables) {
		JSONObject json = messageService.getInformations(uriVariables);
		return json;
	}

	/**
	 * 获取主页直播列表
	 * @return
	 */
	@RequestMapping(value = "/getLatestLiveStreamRoom", method = RequestMethod.POST)
	public RestEntity<LiveStreamRoom> getLatestLiveStreamRoom(long offset, int limit) {
		try{
			RestEntity<LiveStreamRoom> restEntity = liveStreamRoomService.getLiveStreamRoom(null,
					null,null,offset,limit);
			return restEntity;
		}catch(Exception e){
			logger.error("getLatestLiveStreamRoom fail",e);
			return RestEntity.fail("get List fail");
		}
	}
	
	/**
	 * 获取战队
	 * @return
	 */
	@RequestMapping(value = "/getLatestTroops", method = RequestMethod.GET)
	public JSONObject getLatestTroops(@RequestParam Map<String, String> uriVariables){
		JSONObject json = troopsService.getTroopsList(uriVariables);
		return json;
	}
	
}
