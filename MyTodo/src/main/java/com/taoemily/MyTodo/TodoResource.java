package com.taoemily.MyTodo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class TodoResource {

    @Autowired
    private TodoHardCodedService todoHardCodedService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodo(@PathVariable  String username){
        return todoHardCodedService.findAll();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deteleTode(@PathVariable String username, @PathVariable Long id){
        Todo todo=todoHardCodedService.deleteById(id);

        if(todo!=null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }

    @PutMapping(path="/users/{username}/todos/{id}", consumes = {"application/json"})
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username, @PathVariable Long id, @RequestBody Todo todo){
        Todo todoUpdated= todoHardCodedService.save(todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping(path="/users/{username}/todos", consumes = {"application/json"})
    public ResponseEntity<Void> updateTodo(
            @PathVariable String username, @RequestBody Todo todo){
        Todo todoCreated= todoHardCodedService.save(todo);

        //append the url {id}
        URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todoCreated.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


}
