package com.gdsc.EmotionalDiary.service.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoServiceRequest {
    @NotNull
    private String goal;
    @NotNull
    private String category;
    @NotNull
    @Email
    private String userEmail;
    @NotNull
    private LocalDateTime goalTime;

    public static TodoServiceRequest newInstance(String goal, String category, String userEmail, LocalDateTime goalTime) {
        return new TodoServiceRequest(goal, category, userEmail, goalTime);
    }
}
