package com.gdsc.EmotionalDiary.service.diary.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DairyDateServiceResponse {
    List<Date> dates;

    public static DairyDateServiceResponse newInstance(List<Date> dates) {
        return new DairyDateServiceResponse(dates);
    }
}
