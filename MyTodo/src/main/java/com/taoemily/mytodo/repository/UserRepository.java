package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select * from users where email=?1", nativeQuery = true)
    Optional<UserEntity> getUserByEmail(String email);


    @Transactional
    @Modifying
    @Query(value="delete from users where email=?1", nativeQuery = true)
    Integer deleteByEmail(String email);
}
