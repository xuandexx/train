package com.xuandexx.train.api;

import org.springframework.boot.Banner;
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
