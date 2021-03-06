package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query("select todo from Todo todo where todo.userId.id=?1")
    Optional<List<Todo>> findByUserId(Long id);

    @Transactional
    @Modifying
    @Query("delete from Todo todo where todo.todo_id=?1")
    Integer deleteTodo(Long id);


}
