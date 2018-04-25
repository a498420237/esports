package cn.esports.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import cn.esports.entity.ForecastInfo;

@Component
public class ForecastService extends BaseService{

	/**
	 * 获取比赛信息 首页
	 * 
	 * @return
	 */
	public List<ForecastInfo.TBean.ResultBean> GetForecastList(int pageIndex,int size) {
		List<ForecastInfo.TBean.ResultBean> tBean = new ArrayList<ForecastInfo.TBean.ResultBean>();
		try {
			String url = baseConfig.getHttpUrl() + "/quiz/list.json?offset=0&limit=2";
			ForecastInfo models = restTemplate.getForObject(url, ForecastInfo.class);
			tBean=models.getT().getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tBean;
	}
}
