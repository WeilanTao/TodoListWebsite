package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
