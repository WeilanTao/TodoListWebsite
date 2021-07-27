package com.taoemily.mytodo.repository;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.taoemily.mytodo.entity.Todo;
import com.taoemily.mytodo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

        @Query("select todo from Todo todo where todo.userId.id=?1")
        List<Todo> findByUserId(Long id);

        @Transactional
        @Modifying
        @Query("delete from Todo todo where todo.todo_id=?1")
        void deleteTodo(Long id);


}
