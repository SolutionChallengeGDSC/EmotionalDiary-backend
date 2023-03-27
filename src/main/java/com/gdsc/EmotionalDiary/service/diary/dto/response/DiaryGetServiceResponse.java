package com.gdsc.EmotionalDiary.service.diary.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryGetServiceResponse {
    List<DiaryServiceResponse> diaries;

    public static DiaryGetServiceResponse newInstance(List<DiaryServiceResponse> diaries) {
        return new DiaryGetServiceResponse(diaries);
    }
}