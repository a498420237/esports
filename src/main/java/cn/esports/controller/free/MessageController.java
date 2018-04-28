package cn.esports.controller.free;

import cn.esports.controller.BaseController;
import cn.esports.service.MessageService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class MessageController extends BaseController {

    @Autowired
    public MessageService messageService;

    @RequestMapping(value = "/message/messageList", method = RequestMethod.GET)
    public ModelAndView messageList() {
        ModelAndView view = new ModelAndView("message/messageList");
        return view;
    }

    @RequestMapping(value = "message/list", method = RequestMethod.GET)
    public JSONObject getInformations(@RequestParam Map<String, String> uriVariables) {
        return messageService.getInformations(uriVariables);
    }

    @RequestMapping(value = "message/messageTitle", method = RequestMethod.GET)
    public JSONObject getTitles(@RequestParam Map<String, String> uriVariables) {
        return messageService.getTitles(uriVariables);
    }

    @RequestMapping(value = "message/searchByKey", method = RequestMethod.GET)
    public JSONObject getList(@RequestParam Map<String, String> uriVariables) {
        return messageService.searchByKey(uriVariables);
    }

    @RequestMapping(value = "message/messageDetail", method = RequestMethod.GET)
    public JSONObject messageDetail(@RequestParam Map<String, String> uriVariables) {
        return messageService.messageDetail(uriVariables);
    }
}
