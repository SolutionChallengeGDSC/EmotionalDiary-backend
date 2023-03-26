package com.gdsc.EmotionalDiary.service.diary.dto.request;

import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoServiceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryServiceRequest {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    @Email
    private String userEmail;

    public static DiaryServiceRequest newInstance(String title, String content, String userEmail) {
        return new DiaryServiceRequest(title, content, userEmail);
    }
}
