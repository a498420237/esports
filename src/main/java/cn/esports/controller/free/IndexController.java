package cn.esports.controller.free;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import cn.esports.controller.BaseController;
import cn.esports.service.CompetitionTroopsService;

/**
 * 
* <p>Title: IndexController</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */
@RestController
public class IndexController extends BaseController {

	@Autowired
	private CompetitionTroopsService troopsService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("Default");
		view.addObject("zxlist", troopsService.GetTroopsList());
		return view;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView Default() {
		ModelAndView view=new ModelAndView("Default");

		return view;
	}
}
