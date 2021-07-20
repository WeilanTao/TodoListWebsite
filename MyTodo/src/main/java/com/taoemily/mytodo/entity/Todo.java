package com.taoemily.mytodo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "todos")
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="todo_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;

    @Column(name = "todo_name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="target_date", nullable = false)
    private Date date;

    @Column(name="is_done", nullable = false)
    private Boolean isDone;


    @Column(name="user_id", nullable = false)
    private Long userId;

//    @ManyToMany(mappedBy = "users")
//    private List<User> users;

    public Todo(){
        super();
    }
    public Todo( String name, Date date, boolean isDone, Long userId) {
        super();
        this.date = date;
        this.name = name;
        this.isDone = isDone;
        this.userId=userId;

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

//    public List<User> getUsers() {
//        return users;
//    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
