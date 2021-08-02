package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value ="select * from Users where email=?1",nativeQuery = true)
    Optional<UserEntity> getUserByEmail(String email);

    @Query(value ="select user_name from Users where email=?1",nativeQuery = true)
    String getUserNameByEmail(String email);
}
