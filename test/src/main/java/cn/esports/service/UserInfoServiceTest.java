package cn.esports.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

@SpringBootTest
public class UserInfoServiceTest {

	    @Autowired
	    private TestUserInfoService service;
	    
	    @Test
	    public void findOne() throws Exception {
	    	/*UserLogin userLogin = service.SendSMS("13428899017", TYPEENUM.LOGIN);
	    	Scanner scan = new Scanner(System.in);
	    	  String read = scan.nextLine();
	    	service.GetToken("13428899017", read);*/
	    	testMatch();
	    }
	    
	    @Test
	    public void testMatch() {
	    	//String teString = "rememberTap_token=F61604A9164AEB77442920D37634E3A623F9BC11; Path=/";
	    	String teString = "rememberTap_token=F61604A9164AEB77442920D37634E3A623F9BC11; Path=/";
	        Pattern pattern = Pattern.compile("rememberTap_token=(.*);");
	        Matcher matcher = pattern.matcher(teString);
	        if(matcher.find()){
	            System.out.println(matcher.group(1));
	        }
	    }

}
