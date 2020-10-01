package com.kevin.service.ehcache;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(Suite.class)
@SuiteClasses({})
public class MockTests {
	private MockMvc mvc;
	@Autowired
	private WebApplicationContext wac;
	@Test
	public void testA() {
		MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
