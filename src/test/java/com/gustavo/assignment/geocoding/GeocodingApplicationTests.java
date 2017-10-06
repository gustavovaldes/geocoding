package com.gustavo.assignment.geocoding;

import org.apache.camel.CamelContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeocodingApplicationTests {

	@Autowired
	CamelContext camelContext;
	@Test
	public void contextLoads() {

		assertNotNull(camelContext.hasEndpoint("direct:processError"));
		assertNotNull(camelContext.hasEndpoint("direct:googleMapsApi"));
		assertNotNull(camelContext.hasEndpoint("direct:processServiceError"));

	}

}
