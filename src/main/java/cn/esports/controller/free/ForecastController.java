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
import cn.esports.entity.Troops;
import cn.esports.entity.UserLogin;
import cn.esports.service.ForecastService;
import cn.esports.service.UserInfoService;

@RestController
public class ForecastController extends BaseController {

	@Autowired
	public ForecastService fService;
	
	@RequestMapping(value="/forecast", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("forecast");
		//view.addObject("userLogin", fService.GetForecastList(1, 15));
		return view;
	}
	
	@RequestMapping(value="/forecast/list")
	public Map<String, Object> GetList(Integer page,Integer size){
		if(page==null)page=1;
		if(size==null)size=15;
		int pages=page;
		int sizes=size;
		int total=30;
		List<ForecastInfo.TBean.ResultBean> resultBeans=fService.GetForecastList(pages,sizes);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("zxlist", resultBeans);
		map.put("total", total);
		map.put("page", page);
		map.put("size", size);
		return map;
		
	}
	
}
