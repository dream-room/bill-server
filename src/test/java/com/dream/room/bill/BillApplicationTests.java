package com.dream.room.bill;

import com.dream.room.bill.common.jwt.JwtAuthService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BillApplicationTests {


	@Resource
	private JwtAuthService jwtAuthService;

	@Test
	public void testCreateStringToken() throws Exception {
		log.error(jwtAuthService.createStringToken("admin"));
	}

	@Test
	public void contextLoads() {
	}

}
