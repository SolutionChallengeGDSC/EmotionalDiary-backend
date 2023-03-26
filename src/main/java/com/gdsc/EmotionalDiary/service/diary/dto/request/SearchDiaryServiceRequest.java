package com.gdsc.EmotionalDiary.service.diary.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchDiaryServiceRequest {
    private Long id;
    private String userEmail;

    public static SearchDiaryServiceRequest newInstance(Long id, String userEmail) {
        return new SearchDiaryServiceRequest(id, userEmail);
    }
}
