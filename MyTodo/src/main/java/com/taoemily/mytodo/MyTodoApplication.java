package com.taoemily.mytodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import javax.xml.transform.Result;
import java.sql.*;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class MyTodoApplication {

//    String host= "jdbc:mysql://localhost:3306/mytodo?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
//    String userName="root";
//    String userPassWord= "toor";
//    Connection connection;
//
//    {
//        try {
//            connection = DriverManager.getConnection(host, userName,userPassWord);
//            System.out.println("hihihi"+connection);
//
//            Statement statement= connection.createStatement();
//            String sql ="select * from todos";
//            ResultSet set = statement.executeQuery(sql);
//            set.next();
//            int id_col=set.getInt("todo_id");
//            String todoName= set.getString("todo_name");
//            System.out.println(id_col+" "+todoName);
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(MyTodoApplication.class, args);
    }


}
