package com.taoemily.mytodo.service;

import com.taoemily.mytodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserEntityService {
    @Autowired
    private UserRepository userRepository;

    public String getUserNameByEmail(String email){
        return userRepository.getUserNameByEmail(email);
    }

}
