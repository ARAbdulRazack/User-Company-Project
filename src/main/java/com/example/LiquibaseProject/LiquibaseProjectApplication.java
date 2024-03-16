package com.example.LiquibaseProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.LiquibaseProject.Mapper")
public class LiquibaseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiquibaseProjectApplication.class, args);
	}

}
