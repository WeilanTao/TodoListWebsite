package com.taoemily.MyTodo;

import lombok.Data;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;

import java.util.Date;


@Data
public class Todo {
    @NonNull
    private Long id;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private Date date;
    @NonNull
    private Boolean isDone;

    public Todo(Long id, String name, Date date, boolean isDone) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.isDone = isDone;

    }

    //return type has to be the same
    @Override
    public boolean equals(Object object){
        if(object==null || object.getClass()!=getClass()){
            return false;
        }
        if(this==object){
            return true;
        }
        Todo other = (Todo) object;
        if(id!=other.id){
            return false;
        }
        return true;
    }
}
