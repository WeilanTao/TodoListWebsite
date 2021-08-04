package com.taoemily.mytodo.controller;


import com.taoemily.mytodo.dto.LoginRequest;
import com.taoemily.mytodo.dto.LoginResponse;
import com.taoemily.mytodo.dto.SignupRequest;
import com.taoemily.mytodo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
//TODO 数据库没链接抛异常?
        try {
            LoginResponse user = authService.login(loginRequest);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
//            return  ResponseEntity.badRequest().body(e);
        }

        return ResponseEntity.status(401).body("Invald user");

    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signupUser(@RequestBody SignupRequest signupRequest) {
        try {
            return ResponseEntity.ok(authService.saveNewUser(signupRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

//TODO 数据库里报错 409 duplicate user
//        return ResponseEntity.status(409).body("User already exists");
    }

}
