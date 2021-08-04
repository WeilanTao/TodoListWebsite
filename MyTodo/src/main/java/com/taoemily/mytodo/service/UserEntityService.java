package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEntityService {
    @Autowired
    private UserRepository userRepository;

    public String getUserNameByEmail(String email) {
        return userRepository.getUserNameByEmail(email);
    }
    public String getUserIdByEmail(String email) {
        return userRepository.getUserIdByEmail(email);
    }

    public List<UserEntity> getAllUser(){

        List<UserEntity> list= userRepository.findAll();

        for(UserEntity userEntity:list){
            System.out.println(userEntity.toString());
        }

        return list;
    }

}
