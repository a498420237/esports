package cn.esports.controller.free;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.esports.controller.BaseController;

/**
 * 
 * @author huzhimin
 *
 */
@RestController
public class DownLoadController extends BaseController {

	@RequestMapping(value="/download/index", method = RequestMethod.GET)
	public ModelAndView forecast() {
		ModelAndView view =new ModelAndView("/download/index");
		return view;
	}
}
