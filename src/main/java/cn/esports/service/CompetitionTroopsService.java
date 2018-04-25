package cn.esports.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.esports.entity.Troops;

/**
 * Competition赛制>Troops站队
* <p>Title: CompetitionTroopsService</p>  
* <p>Description: </p>  
* @author zhimin.hu  
* @date 2018年4月24日
 */

@Component
public class CompetitionTroopsService extends BaseService {
	
	/**
	 * 站队列表数据
	 * @return
	 */
	public  List<Troops.TBean.ResultBean> GetTroopsList() {
		List<Troops.TBean.ResultBean> list = new ArrayList<Troops.TBean.ResultBean>();
		//String result="";
		try {
			/*ResponseEntity<String> responseEntity = restTemplate
					.getForEntity(baseConfig.getHttpUrl() + "/troops/findTroopsListByApp.json", String.class);
			String jsString = responseEntity.getBody();*/
			//Troops models=restTemplate.getForEntity(baseConfig.getHttpUrl() + "/troops/findTroopsListByApp.json", Troops.class);
			Troops models=restTemplate.getForObject(baseConfig.getHttpUrl() + "/troops/findTroopsListByApp.json", Troops.class);
			//String jString=restTemplate.getForObject(baseConfig.getHttpUrl() + "/troops/findTroopsListByApp.json", String.class);
			list = models.getT().getResult();
			//result=JSON.toJSONString( models.getT().getResult());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
