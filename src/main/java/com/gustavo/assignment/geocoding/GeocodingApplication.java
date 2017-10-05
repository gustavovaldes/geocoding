package com.gustavo.assignment.geocoding;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeocodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocodingApplication.class, args);

	}

	@Bean
	public ServletRegistrationBean restServlet() {
		return new ServletRegistrationBean(
				new CamelHttpTransportServlet(), "/geocoding/*");
	}


}
