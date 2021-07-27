package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.entity.User;
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
@RequestMapping("/mytodo")
public class TodoController {

   @Autowired
    TodoService todoService;

   public void setTodoService(TodoService todoService){
       this.todoService=todoService;
   }

    /**
     * Get all todos for one user
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos")
    public List<Todo> getAllTodo(@RequestParam(required = true) Long userId){
        return todoService.getAllTodos(userId);
    }

    /**
     * Delete one todo
     * @param todoId
     */
    @DeleteMapping("/users/{username}/todos")
    public void deteleTodo(@RequestParam(required = true) Long todoId) {
        todoService.deleteTodo(todoId);
    }

    /**
     * get a todo by id
     * @param todoId
     * @return
     */
    @GetMapping("/users/{username}/todobyid")
    private Todo getTodoById(@RequestParam(required = true)Long todoId){
        return todoService.getTodoById(todoId);//Here @RequestParam is receiving query parameters! not Json!!!
    }

    /**
     * Update a todo
     * @param todo
     * @return
     */
    @PutMapping(path="/users/{username}/todos", consumes = {"application/json"})
    public ResponseEntity<Todo> updateTodo(
            @RequestBody Todo todo){
        todoService.updateTodo(todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }



    @PostMapping(path="/users/{userid}/todos")
    public Todo createTodo(
            @PathVariable long userid, @RequestBody Todo todo){

       //Here in the Json Todo object, the property todo_id  can be omitted; since the Todo entity generates the id automatically!!!
//        return todoService.createTodo(todo);
        return null;

//        //append the url {id}
//        URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(id)
//                .toUri();

//        return ResponseEntity.created(uri).build();
    }


}
