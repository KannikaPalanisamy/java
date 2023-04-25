package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

@ComponentScan(basePackages = {"com.example.demo.*"})

@EnableJpaRepositories({"com.example.demo.*"})

@EnableAutoConfiguration

@EntityScan({"com.example.demo.Model"})
public class Java8FeaturesSpringBootApplication {
	
	public static void main(String[] args) {
		System.out.println("Java 8");
		SpringApplication.run(Java8FeaturesSpringBootApplication.class, args);
	}

}
