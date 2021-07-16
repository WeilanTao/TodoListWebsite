package com.taoemily.MyTodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MyTodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTodoApplication.class, args);
	}

}
