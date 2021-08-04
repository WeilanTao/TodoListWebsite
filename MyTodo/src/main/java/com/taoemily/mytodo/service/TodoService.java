package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.TodoRepository;
import com.taoemily.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public void setTodoRepository(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//    public Long saveTodo(Todo todo){
//        return todoRepository.save(todo);
//    }

    public List<Todo> getAllTodosForAUser(String email) {
        UserEntity userEntity= userRepository.getUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user found with email : " + email));
        return todoRepository.findByUserId(userEntity.getId());
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteTodo(id);
    }

    public Todo getTodoById(Long todoId) {
        return todoRepository.getById(todoId);
    }

    //TODO  throw exception; also check that user can't be changed!
    public void updateTodo(Todo todo) {
        Todo toUpdate = todoRepository.getById(todo.getTodo_id());
        if (toUpdate != null) {
            toUpdate.setName(todo.getName());
            toUpdate.setDescription(todo.getDescription());
            toUpdate.setDate(todo.getDate());
            toUpdate.setIsDone(todo.getIsDone());
//            toUpdate.setUsers(todo.getUsers());
            todoRepository.save(toUpdate);
        }
    }

    /**
     * The bug:  "org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Provided id of the wrong type for class com.taoemily.mytodo.entity.User. Expected: class java.lang.Long, got class com.fasterxml.jackson.annotation.ObjectIdGenerator$IdKey; nested exception is com.fasterxml.jackson.databind.JsonMappingException: Provided id of the wrong type for class com.taoemily.mytodo.entity.User. Expected: class java.lang.Long, got class com.
     */
//    public Todo createTodo(Todo todo) {
//        return todoRepository.save(todo);
//    }


}
