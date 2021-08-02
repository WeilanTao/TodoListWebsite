package com.taoemily.mytodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.xml.transform.Result;
import java.sql.*;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)

public class MyTodoApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyTodoApplication.class, args);
    }


}
