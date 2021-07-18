package com.taoemily.mytodo.dao;


import com.taoemily.mytodo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardCodedService {
    private static List<Todo> todos = new ArrayList();
    private static Long idCounter = 0l;

    static {
        todos.add(new Todo( "Emily Tao", new Date(), true));
        todos.add(new Todo( "Weilan Tao", new Date(), true));
        todos.add(new Todo("Emily Ding", new Date(), true));
        todos.add(new Todo( "Weilan Ding", new Date(), true));

    }

    public List<Todo> findAll() {
        return todos;
    }

    public Todo deleteById(Long id) {
        Todo todo = findById(id);

        if (todo != null)
            todos.remove(todo);

        return todo;
    }

    public Todo save(Todo todo){
        Long id = todo.getTodo_id();
        if(id!=null )
            deleteById(id);
        else
            todo.setTodo_id(++idCounter);

        todos.add(todo);
        return todo;
    }

    private Todo findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getTodo_id() == id) {
                return todo;
            }
        }

        return null;
    }
}
