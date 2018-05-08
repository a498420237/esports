package cn.esports.controller.infos;

import cn.esports.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author <a href="huangxiaofeng@wxchina.com">XiaoFeng Huang</a>
 * @version 1.0.0
 * @description:
 * @date 2018/4/26
 */
@RestController
public class InfosController extends BaseController {

    @RequestMapping(value = "infos/aboutUs", method = RequestMethod.GET)
    public ModelAndView aboutUs(){
        ModelAndView view = new ModelAndView("infos/index");
        return view;
    }
    
    @RequestMapping(value = "infos/websitetrms", method = RequestMethod.GET)
    public ModelAndView websitetrms(){
        ModelAndView view = new ModelAndView("infos/websitetrms");
        return view;
    }
    
    @RequestMapping(value = "infos/beginner", method = RequestMethod.GET)
    public ModelAndView beginner(){
        ModelAndView view = new ModelAndView("infos/beginner");
        return view;
    }
    
    @RequestMapping(value = "infos/officialaccounts", method = RequestMethod.GET)
    public ModelAndView officialaccounts(){
        ModelAndView view = new ModelAndView("infos/officialaccounts");
        return view;
    }

}
