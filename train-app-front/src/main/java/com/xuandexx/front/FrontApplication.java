package com.xuandexx.front;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
@SpringBootApplication
public class FrontApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(FrontApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
