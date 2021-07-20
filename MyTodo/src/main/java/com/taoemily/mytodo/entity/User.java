package com.taoemily.mytodo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "uesr_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "uesr_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

//    @ManyToMany
//    private List<Todo> todoList;

    protected User() {

    }

    public User(String username, String password, String email, Boolean isAdmin, List<Todo> todoList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isAdmin = isAdmin;
//        this.todoList = todoList;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

//    public List<Todo> getTodoList() {
//        return todoList;
//    }
}
