package com.taoemily.mytodo.controller;


import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public UserEntity validateUser(@RequestParam(required = true) String email){
        System.out.println(getUser());
        return userService.loadUserByEmailTest(email);
    }

    private String getUser(){
        String userename="";
        //得到当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal =  authentication.getPrincipal();

        if(principal==null){
            userename="niming";
        }
        if(principal instanceof UserDetails){
            UserDetails userDetails= (UserDetails) principal;
            //这里认证的时候我就把email当作username了所在我认为这里返回的username应该是数据库中的email???
            userename=userDetails.getUsername();
        }else{
            userename=principal.toString();
        }
        return userename;
    }

}
