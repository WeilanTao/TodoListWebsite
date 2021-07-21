package com.taoemily.mytodo.repository;

import com.taoemily.mytodo.entity.Todo;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TodoRepository {
    @Autowired
    private SessionFactory sessionFactory;

    private void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public List<Todo> getAllTodo(Long userId) {
//        System.out.println(userId+"hihihi");
        Session session = this.sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Todo.class);
        criteria.add(Restrictions.eq("userId",userId));
//        List<Todo> todoList = session.createQuery("from Todo", Todo.class).list();
        List<Todo> todoList = criteria.list();
        return todoList;
    }

    public Long save(Todo todo) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(todo);
        return todo.getTodo_id();
    }

    public Todo getTodoById(Long todoId) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query= session.createQuery("from Todo where todo_id=:todoId");
        query.setLong("todoId", todoId);
        System.out.println(todoId+"hihihihihi");
        Todo todo=(Todo) query.uniqueResult();
//        Todo todo = (Todo) session.get(Todo.class, todoId);
        return todo;
    }

    public Todo updateTodo(Todo updateTodo, Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo todo = (Todo) session.get(Todo.class, id);
        if (todo != null) {
            todo.setName(updateTodo.getName());
            todo.setDescription(updateTodo.getDescription());
            todo.setDate(updateTodo.getDate());
            todo.setDone(updateTodo.getDone());
            todo.setUserId(updateTodo.getUserId());
            session.update(todo);
            return todo;
        }
        return null;
    }


    public void deleteTodo(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        Todo todo = (Todo) session.load(Todo.class, id);
        if (null != todo) {
            session.delete(todo);
        }
    }

}
