package com.gdsc.EmotionalDiary.service.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiarySetRequest {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private Date date;
    @NotNull
    private int score;
    @NotNull
    @Email
    private String userEmail;

    public static DiarySetRequest newInstance(Long id, String title, String content, Date date, int score, String userEmail) {
        return new DiarySetRequest(id, title, content, date, score, userEmail);
    }
}