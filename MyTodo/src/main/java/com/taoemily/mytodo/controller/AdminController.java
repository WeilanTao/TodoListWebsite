package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.service.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class AdminController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/admin/getalluser")
    public ResponseEntity<?> getAllUser(){
        //TODO 用户没有权限 要不要抛异常
        try{
            List<UserEntity> userEntities = userEntityService.getAllUser();
            return ResponseEntity.status(200).body(userEntities);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body("not found");
        }
    }
}
