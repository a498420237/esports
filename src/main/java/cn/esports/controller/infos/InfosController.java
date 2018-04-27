package cn.esports.controller.free;

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
        ModelAndView view = new ModelAndView("infos/aboutUs");
        return view;
    }

}
