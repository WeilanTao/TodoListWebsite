package com.taoemily.mytodo.controller;

import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.service.UserEntityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserEntityService userEntityService;

    @GetMapping("/getalluser")
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

    @PutMapping("/adminapprove")
    public ResponseEntity<?> adminapprove(@RequestParam String useremail){
        try{
            userEntityService.setAdmin(useremail);
            return ResponseEntity.ok().body("Set admin succefully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/adminremove")
    public ResponseEntity<?> removeadmin(@RequestParam String useremail, Principal principal){
        try{
            if(useremail.equals(principal.getName()))
                return ResponseEntity.badRequest().body("can't remove your self");
            userEntityService.removeAdmin(useremail);
            return ResponseEntity.ok().body("admin removed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //TODO this is broken !!!
    @DeleteMapping("/deleteuser")
    public ResponseEntity<?> deleteuser(@RequestParam String useremail){
        try{
            userEntityService.deleteUser(useremail);
            return ResponseEntity.ok().body("delete user succefully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
