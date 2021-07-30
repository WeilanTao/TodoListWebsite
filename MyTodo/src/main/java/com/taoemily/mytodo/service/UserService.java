package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.UserEntity;
import com.taoemily.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity getUserEntity = userRepository.getUserByEmail(email);
        if(getUserEntity ==null){
            return null; //userPorvider will throw the exception if it gets a null
        }

        UserDetails userDetails= User.withUsername(getUserEntity.getUsername()).password(getUserEntity.getPassword())
                .authorities("user")
                .build();
        return userDetails;
    }

    public UserEntity loadUserByEmailTest(String email) {
        UserEntity getUser= userRepository.getUserByEmail(email);
        if(getUser!=null){
            return getUser;
        }
        return null;
    }
}
