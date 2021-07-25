package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

        @Query("select todo from Todo todo where todo.userId.id=?1")
        List<Todo> findByUserId(Long id);

}
