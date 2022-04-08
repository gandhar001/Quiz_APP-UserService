package com.SpringBootApp_QuizApp.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = "com.SpringBootApp_QuizApp.Quiz_APP_DataStore.Entities")

@EnableJpaRepositories(basePackages = "com.SpringBootApp_QuizApp.Quiz_APP_DataStore.Repositories")
public class QuizAppUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizAppUserServiceApplication.class, args);
	}

}
