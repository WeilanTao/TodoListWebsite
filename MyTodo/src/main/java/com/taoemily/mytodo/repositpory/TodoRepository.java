package com.taoemily.mytodo.repositpory;

import com.taoemily.mytodo.entity.Todo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository implements JpaRepository<Todo, Long> {

    @Override
    public <S extends Todo> S save(S s) {
        return null;
    }

    @Override
    public List<Todo> findAll() {
        return null;
    }

    @Override
    public List<Todo> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Todo> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Todo> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Todo todo) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Todo> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Todo> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Todo> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Todo> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Todo> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Todo> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Todo getOne(Long aLong) {
        return null;
    }

    @Override
    public Todo getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Todo> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Todo> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Todo> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Todo> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Todo> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Todo> boolean exists(Example<S> example) {
        return false;
    }
}
