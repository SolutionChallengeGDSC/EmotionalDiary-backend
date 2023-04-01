package com.gdsc.EmotionalDiary.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Diary save(Diary diary);
    Optional<Diary> findById(Long id);
    List<Diary> findByDate(Date date);
    @Override
    void deleteById(Long id);
    List<Diary> findAll();
}