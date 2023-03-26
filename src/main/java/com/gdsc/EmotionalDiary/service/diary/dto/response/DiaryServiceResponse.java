package com.gdsc.EmotionalDiary.service.diary.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryServiceResponse {
    Long id;
    String title;
    String content;
    boolean isprivate;
    LocalDateTime createdAt;

    public static DiaryServiceResponse of(Long id, String title, String content, boolean isprivate, LocalDateTime createdAt) {
        return new DiaryServiceResponse(id, title, content, isprivate, createdAt);
    }
}
