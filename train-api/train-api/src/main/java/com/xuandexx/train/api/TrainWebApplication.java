package com.xuandexx.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrainWebApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TrainWebApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
