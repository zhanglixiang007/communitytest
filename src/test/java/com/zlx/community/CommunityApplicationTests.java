package com.zlx.community;

import com.zlx.community.dao.DiscussPostMapper;
import com.zlx.community.dao.UserMapper;
import com.zlx.community.entity.DiscussPost;
import com.zlx.community.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
@Slf4j
class CommunityApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DiscussPostMapper discussPostMapper;

	@Test
	void testUserMapper() {
		User user = new User();
		user.setUsername("张三");
		user.setPassword("zhangsan");
		user.setSalt("ldfl");
		user.setEmail("zhangsan@qq.com");
		user.setType(0);
		user.setStatus(1);
		user.setActivationCode("jihuoma");
		user.setHeaderUrl("http://www.nowcoder.com/zlx.png");
		user.setCreateTime(new Date());

		userMapper.insertUser(user);
		int id = user.getId();
		System.out.println(id);

		user = userMapper.selectById(id);
		System.out.println(user);

		user = userMapper.selectByUsername("张三");
		System.out.println(user);

		user = userMapper.selectByEmail("zhangsan@qq.com");
		System.out.println(user);

		int res = userMapper.updateStatusById(id, 2);
		System.out.println(res);

		res = userMapper.updateHeaderUrlById(id, "http://www.zlx.com");
		System.out.println(res);

		res = userMapper.updatePasswordById(id, "zlx007");
		System.out.println(res);
	}

	@Test
	public void testDiscussPostMapper(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
		for(DiscussPost discussPost:discussPosts){
			System.out.println(discussPost);
		}
		for(DiscussPost discussPost:discussPosts){
			System.out.println(simpleDateFormat.format(discussPost.getCreateTime()));
		}

		int rows = discussPostMapper.selectDiscussPostsRows(0);
		System.out.println(rows);
	}

	@Test
	public void testLogger(){
		Logger logger = LoggerFactory.getLogger(CommunityApplicationTests.class);
		System.out.println(logger.getName());
		System.out.println(logger.getClass());
		logger.trace("trace信息");
		logger.debug("debug信息");
		logger.info("info信息");
		logger.warn("warn信息");
		logger.error("error信息");
	}

	/*在类的上方加上Lombok项目的@Slf4j注解之后，会自动在当前类中创建一个Logger对象，名为log，然后就可以在类中直接使用该对象打印日志，
	跟testLogger中自己创建Logger对象是等价的*/
	@Test
	public void testLoggerOfLombok(){
		System.out.println(log.getName());
		System.out.println(log.getClass());
		log.trace("trace信息");
		log.debug("debug信息");
		log.info("info信息");
		log.warn("warn信息");
		log.error("error信息");
	}

}
