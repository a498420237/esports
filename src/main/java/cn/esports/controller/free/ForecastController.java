package cn.esports.controller.free;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.esports.controller.BaseController;
import cn.esports.entity.ForecastInfo;
import cn.esports.service.ForecastService;

@RestController
public class ForecastController extends BaseController {

	@Autowired
	public ForecastService fService;
	
	@RequestMapping(value="/forecast/index", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("forecast/index");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/forecast/list")
	public Map<String, Object> GetList(Integer pageindex,Integer pagesize,int gameId){
		if(pageindex==null)pageindex=1;
		if(pagesize==null)pagesize=10;
		int total=50;
		List<ForecastInfo.TBean.ResultBean> resultBeans=fService.GetForecastList(pageindex.intValue(),pagesize.intValue(),gameId);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("resultlist", resultBeans);
		map.put("total", total);
		map.put("pageindex", pageindex);
		map.put("pagesize", pagesize);
		return map;
		
	}
	
}
