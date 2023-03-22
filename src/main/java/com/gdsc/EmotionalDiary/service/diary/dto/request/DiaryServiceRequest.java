package com.gdsc.EmotionalDiary.service.diary.dto.request;

import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoServiceRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
