package com.xuandexx.user;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(UserApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		// 添加监听器
		// app.addListeners(new MyListener());
		application.run(args);
	}

}
