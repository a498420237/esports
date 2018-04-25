package cn.esports.controller.free;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import cn.esports.controller.BaseController;
import cn.esports.entity.Troops;
import cn.esports.service.CompetitionTroopsService;
import cn.esports.service.MatchService;

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
	
	@Autowired
	private MatchService matchService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView("Default");
		//view.addObject("zxlist", troopsService.GetTroopsList());
		return view;
	}
	
	@RequestMapping("/list")
	public Map<String, Object> GetList(Integer page,Integer size){
		if(page==null)page=1;
		if(size==null)size=15;
		int pages=page;
		int sizes=size;
		int total=30;
		List<Troops.TBean.ResultBean> resultBeans=GetList(pages,sizes);
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("zxlist", resultBeans);
		map.put("total", total);
		map.put("page", page);
		map.put("size", size);
		return map;
		
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView Default() {
		ModelAndView view=new ModelAndView("index");
		view.addObject("matchlist", matchService.GetIndexList(""));
		return view;
	}
	
	public List<Troops.TBean.ResultBean> GetList(int page ,int size) {
		List<Troops.TBean.ResultBean> list=new ArrayList<Troops.TBean.ResultBean>();
		int start=page*size;
		for (int i = start; i < start+size; i++) {
			Troops.TBean.ResultBean bean =new Troops.TBean.ResultBean();
			bean.setCaptainId(5);
			bean.setGameType(10);
			bean.setId(i);
			bean.setStatus("0");
			bean.setTroopsId("ZZAAA"+i);
			bean.setTroopsManifesto("这是一支强力的站队"+i);
			bean.setTroopsName("战队大名"+i);
			bean.setTroopsNumbers(i+"人");
			bean.setTroopsUrl("");
			list.add(bean);
		}
		return list;
	}
}
