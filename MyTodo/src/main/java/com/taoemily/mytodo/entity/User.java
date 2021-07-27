package com.taoemily.mytodo.entity;

import com.fasterxml.jackson.annotation.*;
import com.taoemily.mytodo.util.UserIdResolver;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = false)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIdentityReference(alwaysAsId = true)
@Proxy(lazy = false)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
//    @JsonIdentityReference(alwaysAsId = false)
    @JoinColumn( name ="user_id", referencedColumnName = "user_id")
    private List<Todo> todoList= new ArrayList<>();

    protected User() {
        super();
    }

    public User( String username, String password, String email, Boolean isAdmin) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }



    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
