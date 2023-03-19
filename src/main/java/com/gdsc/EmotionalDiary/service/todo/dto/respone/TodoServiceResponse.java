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
    LocalDateTime createdAt;

    public static TodoServiceResponse of(Long id, String goal, boolean success, String category, LocalDateTime createdAt) {
        return new TodoServiceResponse(id, goal, success, category, createdAt);
    }
}
