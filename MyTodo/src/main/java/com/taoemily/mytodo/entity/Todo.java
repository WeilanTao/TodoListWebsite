package com.taoemily.mytodo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "todos")
@Proxy(lazy = false)
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
    private UserEntity userId;

    public Todo(String name, Date date, boolean isDone) {
        super();
        this.date = date;
        this.name = name;
        this.isDone = isDone;
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

}
