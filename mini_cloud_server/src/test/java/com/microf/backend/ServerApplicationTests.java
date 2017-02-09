package com.microf.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerCommonConfigurationTest.class, ServerConfigurationTest.class})
public class ServerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
