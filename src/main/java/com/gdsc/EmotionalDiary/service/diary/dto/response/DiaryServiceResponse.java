package com.gdsc.EmotionalDiary.service.diary.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryServiceResponse {
    Long id;
    String title;
    String content;
    Date date;

    public static DiaryServiceResponse of(Long id, String title, String content, Date date) {
        return new DiaryServiceResponse(id, title, content, date);
    }
}
