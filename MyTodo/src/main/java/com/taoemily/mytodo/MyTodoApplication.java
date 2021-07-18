package com.taoemily.mytodo;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.repositpory.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
@EnableWebSecurity
public class MyTodoApplication  implements CommandLineRunner {

	@Autowired
	TodoRepository todoRepository;


	public static void main(String[] args) {
		SpringApplication.run(MyTodoApplication.class, args);
	}

	@Override
	public void run (String ... args) throws  Exception{
		Date date= Calendar.getInstance().getTime();
		Todo todo = new Todo("to say hi", date, false);
		this.todoRepository.save(todo);
	}

}
