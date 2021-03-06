package cn.esports.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="huangxiaofeng@wxchina.com">XiaoFeng Huang</a>
 * @version 1.0.0
 * @description:
 * @date 2018/4/27
 */
@Service
public class LiveService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(LiveService.class);

    public static final String LIVE_RECOMMENDLIVE = "live/recommendLive.json";
    public static final String LIVE_LIST = "live/list.json";
    public static final String LIVE_DETAIL = "live/liveInfo.json";
    //    public static final String LIVE_GAME_LIST = "quiz/gameInfo/list.json";
    public static final String LIVE_GAME_LIST = "quiz/gameInfo/list.json"; //  用来测试的


    /**
     * 推荐的直播
     *
     * @param uriVariables
     * @return
     */
    public JSONObject getRecommendLive(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl(LIVE_RECOMMENDLIVE, uriVariables), JSONObject
                    .class);
        } catch (RestClientException e) {
            logger.error("call the getList from rest api occurred error,cause by:", e);
            return null;
        }
    }

    /**
     *
     * @param uriVariables
     * @return
     */
    public JSONObject getList(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl(LIVE_LIST, uriVariables), JSONObject.class);
        } catch (RestClientException e) {
            logger.error("call the getList from rest api occurred error,cause by:", e);
            return null;
        }
    }

    /**
     *  获取直接游戏列表
     * @param uriVariables
     * @return
     */
    public JSONObject getGameList(Map<String, String> uriVariables) {
        try {
            return restTemplate.getForObject(createUrl(LIVE_GAME_LIST, uriVariables), JSONObject.class);
        } catch (RestClientException e) {
            logger.error("call the getList from rest api occurred error,cause by:", e);
            return null;
        }
    }


    /**
     * 直播详情
     *
     * @param liveId
     * @return
     */
    public JSONObject liveDetailbyId(Long liveId) {
        try {
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("liveId", String.valueOf(liveId));
            return restTemplate.getForObject(createUrl(LIVE_DETAIL, uriVariables), JSONObject
                    .class);
        } catch (RestClientException e) {
            logger.error("call the liveDetailbyId from rest api occurred error,cause by:", e);
            return null;
        }
    }


}
