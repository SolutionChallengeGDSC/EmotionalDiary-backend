package com.gdsc.EmotionalDiary.service.diary.dto.request;

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
    private Boolean privateStatus;
    @NotNull
    @Email
    private String userEmail;

    public static DiaryServiceRequest newInstance(String title, String content, Boolean privateStatus, String userEmail) {
        return new DiaryServiceRequest(title, content, privateStatus, userEmail);
    }
}
