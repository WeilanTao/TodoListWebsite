package com.taoemily.mytodo.service;

import com.taoemily.mytodo.entity.User;
import com.taoemily.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById (Long userId){return userRepository.getById(userId);}

}
