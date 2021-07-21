package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public void setTodoRepository(TodoRepository todoRepository){
        this.todoRepository= todoRepository;
    }

    public Long saveTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos(Long userId){
        return todoRepository.getAllTodo(userId);
    }

    public Todo getTodoById(Long todoId){
        return todoRepository.getTodoById(todoId);
    }

    public Todo updateTodo(Todo updateTodo, Long id){
        return todoRepository.updateTodo(updateTodo,id);
    }

    public void deleteTodo(Long id){
         todoRepository.deleteTodo(id);
    }
//    public Long createTodo(Todo todo){
//        return todoRepository.createTodo(todo);
//    }


}
