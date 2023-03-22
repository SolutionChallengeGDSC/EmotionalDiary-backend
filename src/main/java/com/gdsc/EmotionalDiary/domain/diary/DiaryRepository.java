package com.gdsc.EmotionalDiary.domain.diary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary save(Diary diary);
    Optional<Diary> findById(Long id);
    @Override
    void deleteById(Long aLong);
    Page<Diary> findAll(Specification<Diary> spec, Pageable pageable);
}