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

    public void deleteTodo(Long id, String principleemail){

        try {
            Integer deletion = todoRepository.deleteTodo(id);

            if(deletion==0)
                throw new RuntimeException("Invaid deletion. No Such id");

            System.out.println(deletion);
        }
        catch(RuntimeException e){
            throw new RuntimeException("Invaid deletion");
        }
//TODO
//        UserEntity principleuser = userRepository.getUserByEmail(principleemail).orElseThrow(()->new RuntimeException("illegal deletion"));
//        if(deletedTodo.getUserId()!=principleuser){
//            throw new RuntimeException("illegal deletion");
//        }

    }

    public Todo getTodoById(Long todoId) {
        return todoRepository.getById(todoId);
    }

    //TODO  throw exception; also check that user can't be changed!
    public void updateTodo(Todo todo) {
        try{
        Todo toUpdate = todoRepository.getById(todo.getTodo_id());
        if (toUpdate != null && todo.getUserId()==null) {
            toUpdate.setName(todo.getName());
            toUpdate.setDescription(todo.getDescription());
            toUpdate.setDate(todo.getDate());
            toUpdate.setIsDone(todo.getIsDone());
            todoRepository.save(toUpdate);
        }else
            throw new RuntimeException("Invalid input update data");
        }
        catch(RuntimeException e){
            throw new RuntimeException("failed to update the user");
        }
    }

    public Todo createTodo(Todo todo, String principleemail) {
        UserEntity principleuser = userRepository.getUserByEmail(principleemail).orElseThrow(()->new RuntimeException("illegal deletion"));
        Todo toSave = new Todo();
        toSave.setName(todo.getName());
        toSave.setDate(todo.getDate());
        toSave.setIsDone(todo.getIsDone());
        Todo saved= todoRepository.save(toSave);

        List<Todo> todoList=principleuser.getTodoList();
        todoList.add(saved);
        principleuser.setTodoList(todoList);

        userRepository.save(principleuser);
        
        return todo;
    }


}
