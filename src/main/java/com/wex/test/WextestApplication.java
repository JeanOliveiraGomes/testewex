package com.wex.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.wex.test.controller.TransactionController;
import com.wex.test.service.TransactionService;

@EnableFeignClients
@SpringBootApplication

@EnableWebMvc
public class WextestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WextestApplication.class, args);
	}

}
