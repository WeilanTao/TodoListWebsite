package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.exception.AdminIdentityConflictException;
import com.taoemily.mytodo.exception.UserNotExistException;
import com.taoemily.mytodo.repository.TodoRepository;
import com.taoemily.mytodo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserEntityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    public UserEntity getUserByEmail(String email) {
        UserEntity userEntity = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("No user found Invalid User email account"));

        return userEntity;
    }

    public List<UserEntity> getAllUser() {

        List<UserEntity> list = userRepository.findAll();

        for (UserEntity userEntity : list) {
            System.out.println(userEntity.toString());
        }

        return list;
    }

    public void setAdmin(String useremail) throws  AdminIdentityConflictException,UserNotExistException{
        try {
            UserEntity userEntity = userRepository.getUserByEmail(useremail)
                    .orElseThrow(() ->  new UserNotExistException("No such user."));

            if(userEntity.getIsAdmin()==true)
                throw new AdminIdentityConflictException("can't set this user since this user has already been an admin");


                userEntity.setIsAdmin(true);
                userRepository.saveAndFlush(userEntity);

        }
        catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeAdmin(String useremail) throws UserNotExistException,AdminIdentityConflictException{
        try {
            UserEntity userEntity = userRepository.getUserByEmail(useremail)
                    .orElseThrow(() ->  new UserNotExistException("No such user."));

            if (userEntity.getIsAdmin()) {
                userEntity.setIsAdmin(false);
                userRepository.saveAndFlush(userEntity);
            }else{
                throw new AdminIdentityConflictException("can't remove this user since this user is not an admin");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteUser(String useremail) {
        try {
            UserEntity userEntity = userRepository.getUserByEmail(useremail)
                    .orElseThrow(() -> new RuntimeException("No such user."));

            userRepository.delete(userEntity);


        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
