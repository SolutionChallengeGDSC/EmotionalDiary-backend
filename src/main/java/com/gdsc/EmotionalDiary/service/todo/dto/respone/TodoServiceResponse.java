package com.gdsc.EmotionalDiary.service.todo.dto.respone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoServiceResponse {
    Long id;
    String goal;
    boolean success;
    String category;
    boolean isRecommend;
    LocalDateTime createdAt;
    LocalDateTime goalTime;

    public static TodoServiceResponse of(Long id, String goal, boolean success, String category, boolean isRecommend, LocalDateTime createdAt, LocalDateTime goalTime) {
        return new TodoServiceResponse(id, goal, success, category, isRecommend, createdAt, goalTime);
    }
}
