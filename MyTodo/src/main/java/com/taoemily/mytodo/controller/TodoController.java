package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class TodoController {

    @Autowired
    TodoService todoService;

    /**
     * Get all todos for one user
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/todos")
    public List<Todo> getAllTodo(Principal principal) {
        String email = principal.getName();
        return todoService.getAllTodosForAUser(email);
    }

    /**
     * Delete one todo
     *
     * @param todoId
     */
    @DeleteMapping("/deletetodos")
    public void deteleTodo(@RequestParam(required = true) Long todoId, Principal principal) {
        todoService.deleteTodo(todoId, principal.getName());
    }

    /**
     * get a todo by id
     *
     * @param todoId
     * @return
     */
//    @GetMapping("/gettodobyid")
//    private Todo getTodoById(@RequestParam(required = true) Long todoId,  Principal principal) {
//        return todoService.getTodoById(todoId);//Here @RequestParam on postman is receiving query parameters! not Json!!!
//    }

    /**
     * Update a todo
     *
     * @param todo
     * @return
     */
    @PutMapping(path = "/updatetodo", consumes = {"application/json"})
    public ResponseEntity<?> updateTodo(@RequestBody Todo todo, Principal principal) {
        try {
            //TODO todouser has to be the same as principle
            todoService.updateTodo(todo);
            return ResponseEntity.ok("updated successfully");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping(path = "/createtodos")
    public ResponseEntity<?>  createTodo( @RequestBody Todo todo, Principal principal) {
        //Here in the Json Todo object, the property todo_id  can be omitted; since the Todo entity generates the id automatically!!!
        try{
            Todo createdTodo = todoService.createTodo(todo, principal.getName());
            return new ResponseEntity<Todo>(createdTodo, HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
