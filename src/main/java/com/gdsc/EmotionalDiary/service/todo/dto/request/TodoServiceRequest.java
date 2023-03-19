package com.gdsc.EmotionalDiary.service.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static TodoServiceRequest newInstance(String goal, String category, String userEmail) {
        return new TodoServiceRequest(goal, category, userEmail);
    }
}
