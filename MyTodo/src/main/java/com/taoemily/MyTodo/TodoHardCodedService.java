package com.taoemily.MyTodo;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoHardCodedService {
    private static List<Todo> todos = new ArrayList();
    private static Long idCounter = 0l;

    static {
        todos.add(new Todo(++idCounter, "Emily Tao", new Date(), true));
        todos.add(new Todo(++idCounter, "Weilan Tao", new Date(), true));
        todos.add(new Todo(++idCounter, "Emily Ding", new Date(), true));
        todos.add(new Todo(++idCounter, "Weilan Ding", new Date(), true));

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
        System.out.println("hihihi"+todo.getId());
        Long id = todo.getId();
//


        todos.add(todo);
        return todo;
    }

    private Todo findById(Long id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }

        return null;
    }
}
