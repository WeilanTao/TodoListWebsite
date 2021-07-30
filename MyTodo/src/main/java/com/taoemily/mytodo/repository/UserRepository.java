package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select user from UserEntity user where user.email=?1")
    UserEntity getUserByEmail(String email);
}
