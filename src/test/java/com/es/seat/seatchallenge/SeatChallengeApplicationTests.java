package com.es.seat.seatchallenge;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class SeatChallengeApplicationTests {

	@Autowired
	private WebApplicationContext context;

	@Test
	void contextLoads() {
		Assert.assertNotNull(context);
	}

}
