package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.TodoRepository;
import com.taoemily.mytodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public List<Todo> getAllTodosForAUser(String email) {
        UserEntity userEntity = userRepository.getUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("No user found with email : " + email));
        return todoRepository.findByUserId(userEntity.getId())
                .orElseThrow(() -> new RuntimeException("No user found"));
    }

    public void deleteTodo(Long id, String principleemail) {

        try {
            Integer deletion = todoRepository.deleteTodo(id);

            if (deletion == 0)
                throw new RuntimeException("Invaid deletion. No Such id");

            System.out.println(deletion);
        } catch (RuntimeException e) {
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

    public void updateTodo(Todo todo) {
        try {
            Todo toUpdate = todoRepository.getById(todo.getTodo_id());
            if (toUpdate != null && todo.getUserId() == null) {
                toUpdate.setName(todo.getName());
                toUpdate.setDescription(todo.getDescription());
                toUpdate.setDate(todo.getDate());
                todoRepository.save(toUpdate);
            } else
                throw new RuntimeException("Invalid input update data");
        } catch (RuntimeException e) {
            throw new RuntimeException("failed to update the user");
        }
    }

    public void doneTodo(Long id){
        try {
            Todo toUpdate = todoRepository.getById(id);
            if (toUpdate != null ) {

                toUpdate.setIsDone( !toUpdate.getIsDone()) ;
                todoRepository.save(toUpdate);
            } else
                throw new RuntimeException("Invalid input update data");
        } catch (RuntimeException e) {
            throw new RuntimeException("failed to update the user");
        }
    }


    public void createTodo(Todo todo, String principleemail) {
        try {
            UserEntity principleuser = userRepository.getUserByEmail(principleemail).orElseThrow(() -> new RuntimeException("illegal deletion"));
            Todo toSave = new Todo();
            if (!StringUtils.hasText(todo.getName()) || todo.getDate() == null || todo.getIsDone() == null || todo.getUserId() != null) {
                throw new RuntimeException("bad request info");
            }
            toSave.setName(todo.getName());
            toSave.setDate(todo.getDate());
            toSave.setDescription(todo.getDescription());
            toSave.setIsDone(todo.getIsDone());

            System.out.println("++++++++++++++++++++++++++++++++++++++"+toSave.toString());

            Todo saved = todoRepository.save(toSave);

            List<Todo> todoList = principleuser.getTodoList();
            todoList.add(saved);
            principleuser.setTodoList(todoList);

            userRepository.save(principleuser);

        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
