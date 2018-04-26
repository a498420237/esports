package cn.esports.service;

import cn.esports.utils.RestTemplateUtils;
import cn.esports.entity.LoginEntity;
import cn.esports.entity.ResultEntity;
import cn.esports.entity.UserInfo;
import cn.esports.service.BaseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * Competition赛制>Troops站队
 * <p>Title: CompetitionTroopsService</p>
 * <p>Description: </p>
 *
 * @author zhimin.hu
 * @date 2018年4月24日
 */

@Component
public class UserService extends BaseService {


    /**
     * 发送短信
     *
     * @return
     */
    public ResultEntity sendMobileCode(String mobile, String type) {

        ResultEntity resultEntity = RestTemplateUtils.post(baseConfig.getHttpUrl() + "/api/msg/sendMobileCode.json",

                "{\"mobile\": \"" + mobile +
                        "\",\"type\": \"" + type + "\" }",

                "{\"TAP-CLIENT-TYPE\": \"" + TAPCLIENTTYPE +
                        "\",\"TAP-CLIENT-VERSION\": \"" + TAPCLIENTVERSION + "\" }",

                ResultEntity.class, null);

        return resultEntity;
    }


    /**
     * 登录或注册，并获取token
     *
     * @return
     */
    public LoginEntity login(String mobile, String code) {

        LoginEntity loginEntity = RestTemplateUtils.post(baseConfig.getHttpUrl() + "/api/index/login.json",

                "{\"mobile\": \"" + mobile +
                        "\",\"code\": \"" + code + "\" }",

                "{\"TAP-CLIENT-TYPE\": \"" + TAPCLIENTTYPE +
                        "\",\"TAP-CLIENT-VERSION\": \"" + TAPCLIENTVERSION + "\" }",

                LoginEntity.class, null);


        if (loginEntity.getCode() == 200) {

            Subject currentUser = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(String.valueOf(loginEntity.getT().getId()), loginEntity.getT().getUserName());

            Session session = currentUser.getSession();
            session.setAttribute("token", loginEntity.getT().getToken());

            //String value = (String) session.getAttribute("token");

            if (!currentUser.isAuthenticated()) {
                try {
                    currentUser.login(token);
                } catch (UnknownAccountException uae) {
                    //log.info("-->There is no user with username of " + token.getPrincipal());
                } catch (IncorrectCredentialsException ice) {
                    //log.info("-->Password for account " + token.getPrincipal() + " was incorrect!");
                } catch (LockedAccountException lae) {
                    //log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                }
            }

        }

        return loginEntity;
    }

    public void loginout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }


    /**
     * 获取用户资料信息
     *
     * @return
     */
    public UserInfo getUserInfo(String jsonparams) {

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
             return null;
        }

        Session session = currentUser.getSession();

        String token = (String) session.getAttribute("token");

        if (token==null){
            return null;
        }

        UserInfo userinfo = RestTemplateUtils.post(baseConfig.getHttpUrl() + "/api/index/login.json",
                jsonparams,
                "{\"TAP-CLIENT-TYPE\": \"" + TAPCLIENTTYPE +
                        "\",\"TAP-CLIENT-VERSION\": \"" + TAPCLIENTVERSION +
                        "\",\"token\":" + token + " }",
                UserInfo.class, null);

        return userinfo;
    }
}
