package com.demo.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.demo.dao", "com.demo.service", "com.demo.controller"})
@EnableTransactionManagement
@EntityScan(basePackages = "com.demo.model")
public class MainSpringBoot extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MainSpringBoot.class, args);
	}

}
