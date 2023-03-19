package com.gdsc.EmotionalDiary.domain.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    Todo save(Todo todo);
    Optional<Todo> findById(Long id);
    @Override
    void deleteById(Long aLong);
    Page<Todo> findAll(Specification<Todo> spec, Pageable pageable);
}
