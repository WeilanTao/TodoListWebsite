package com.taoemily.MyTodo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuthenticationController {

    @GetMapping("/basicauth")
    public AuthenticationBean authBean(){
        return new AuthenticationBean("You are authenticated");
    }
}
