package com.gmail.yauhen2012.webmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class,
		scanBasePackages = {
		"com.gmail.yauhen2012.repository",
		"com.gmail.yauhen2012.service",
		"com.gmail.yauhen2012.webmodule"})
public class WebModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebModuleApplication.class, args);
	}

}
