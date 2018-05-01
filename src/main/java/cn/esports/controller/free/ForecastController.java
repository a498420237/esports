package cn.esports.controller.free;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.esports.controller.BaseController;
import cn.esports.service.ForecastService;

@RestController
public class ForecastController extends BaseController {

	@Autowired
	public ForecastService forecastService;
	
	@RequestMapping(value="/forecast/index", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("forecast/index");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/forecast/list", method = RequestMethod.GET)
	public JSONObject getList(@RequestParam Map<String, String> uriVariables){
		return forecastService.getForecastList(uriVariables);
	}
	
	@RequestMapping(value="/forecast/gemelist", method = RequestMethod.GET)
	public JSONObject getGameInfoList(@RequestParam Map<String, String> uriVariables){
		return forecastService.getGameInfoList(uriVariables);
	}
	@RequestMapping(value="/forecast/getLotteryInfo", method = RequestMethod.GET)
	public JSONObject getLotteryInfo(@RequestParam Map<String, String> uriVariables){
		return forecastService.getLotteryInfo(uriVariables);
	}
	@RequestMapping(value="/forecast/addLotteryInfo", method = RequestMethod.GET)
	public JSONObject addLotteryInfo(@RequestParam Map<String, String> uriVariables){
		return forecastService.addLotteryInfo(uriVariables);
	}
	
}
