package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.repository.TodoRepository;
import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TodoController {

   @Autowired
    TodoService todoService;

   public void setTodoService(TodoService todoService){
       this.todoService=todoService;
   }
    @GetMapping("/users/{userId}/todos")
    public List<Todo> getAllTodo(@PathVariable  Long userId){
        return todoService.getAllTodos(userId);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public void deteleTode(@PathVariable Todo deleteTodo, @PathVariable Long id){
         todoService.deleteTodo(id);

//        if(deletedTodo!=null)
//            return ResponseEntity.noContent().build();
//
//        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/users/{username}/todos/{id}", consumes = {"application/json"})
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username, @PathVariable Long id, @RequestBody Todo todo){
        Todo todoUpdated= todoService.updateTodo(todo, id);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping(path="/users/{username}/todos", consumes = {"application/json"})
    public ResponseEntity<Void> createTodo(
            @PathVariable String username, @RequestBody Todo todo){
        Long id= todoService.saveTodo(todo);

        //append the url {id}
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
