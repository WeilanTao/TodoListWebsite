package com.taoemily.mytodo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "todos")
@JsonIgnoreProperties(ignoreUnknown = false)
public class Todo {

    @Id
    @Column(name = "todo_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;

    @Column(name = "todo_name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "target_date", nullable = false)
    private Date date;

    @Column(name = "is_done", nullable = false)
    private Boolean isDone;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;


    public Todo() {
        super();
    }

    public Todo(String name, Date date, boolean isDone) {
        super();
        this.date = date;
        this.name = name;
        this.isDone = isDone;
//        this.userId=userId;

    }

    //return type has to be the same
    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        if (this == object) {
            return true;
        }
        Todo other = (Todo) object;
        if (todo_id != other.todo_id) {
            return false;
        }
        return true;
    }

    public Long getTodo_id() {
        return todo_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setTodo_id(Long todo_id) {
        this.todo_id = todo_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public User getUsers() {
        return userId;
    }

    public void setUsers(User users) {
        this.userId = users;
    }


}
