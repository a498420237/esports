package cn.esports.controller.free;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.esports.controller.BaseController;
import cn.esports.service.CompetitionService;

import com.alibaba.fastjson.JSONObject;

/**
 * 赛事模块 控制层
 * @author liukunrui
 */
@RestController
public class CompetitionController extends BaseController {

	@Autowired
	public CompetitionService competitionService;
	
	@RequestMapping(value="/competition/index", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("competition/index");
		return view;
	}
	
	/**
	 * 赛事列表
	 * @param uriVariables
	 * @return
	 */
	@RequestMapping(value="/competition/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return competitionService.getCompetitions(uriVariables);
	}
	
	/**
	 * 赛事详情
	 * @param matchId
	 * @return
	 */
	@RequestMapping(value="/competition/info/{matchId}", method = RequestMethod.GET)
	public ModelAndView getInfo(@PathVariable Long matchId){
		
		JSONObject result = competitionService.getCompetitionInfo(matchId);
		ModelAndView view =new ModelAndView("competition/info");
		Object competition = result.get("t"); 
		view.addObject("competition", competition);
		return view;
	}
}
