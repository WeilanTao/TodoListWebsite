package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.entity.User;
import com.taoemily.mytodo.repository.TodoRepository;
import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET, path = "/users/{userName}/todos")
    public List<Todo> getAllTodo(@RequestParam(required = true) Long userId){
//       User toSend  = user;
//        System.out.println("hello hello hello" + toSend.getId());
//        return null;
        System.out.println(userId);
        return todoService.getAllTodos(userId);
    }

//    @RequestMapping(method = RequestMethod.POST, path="/users/{userid}/todosById", produces = "application/json")
//    public Todo getTodoByTodoId(
//            @PathVariable(value="userid") Long userid, @RequestParam(required = true) Long todoid){ //Here @RequestParam is receiving query parameters! not Json!!!
//        System.out.println("HELLO HELLO HELLO HELLO "+ todoid);
//        Todo getTodo= todoService.getTodoById(todoid);
//        return getTodo;
//    }
//
//    @DeleteMapping("/users/{username}/todos/{id}")
//    public void deteleTode(@PathVariable Todo deleteTodo, @PathVariable Long id){
//         todoService.deleteTodo(id);

//        if(deletedTodo!=null)
//            return ResponseEntity.noContent().build();
//
//        return ResponseEntity.notFound().build();
//    }
//
//    @PutMapping(path="/users/{username}/todos/{id}", consumes = {"application/json"})
//    public ResponseEntity<Todo> updateTodo(
//            @PathVariable String username, @PathVariable Long id, @RequestBody Todo todo){
//        Todo todoUpdated= todoService.updateTodo(todo, id);
//        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
//    }

//    @PostMapping(path="/users/{userid}/todos", consumes = {"application/json"})
//    public ResponseEntity<Void> createTodo(
//            @PathVariable long userid, @RequestBody Todo todo){
//       //Here in the Json Todo object, the property todo_id  can be omitted; since the Todo entity generates the id automatically!!!
//        Long id= todoService.saveTodo(todo);
//
//        //append the url {id}
//        URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}").buildAndExpand(id)
//                .toUri();
//
//        return ResponseEntity.created(uri).build();
//    }


}
