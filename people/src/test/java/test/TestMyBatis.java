package test;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import com.alibaba.fastjson.JSON;
import com.people.pojo.User;
import com.people.service.UserService;

@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {

	private static Logger logger = Logger.getLogger(TestMyBatis.class);
	
	
	@Resource
	private UserService userService;

	@Test
	public void test1() {
		User user = userService.getUserById(1);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
		logger.info(JSON.toJSONString(user));
	}
}
