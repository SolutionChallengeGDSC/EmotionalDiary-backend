package com.gdsc.EmotionalDiary.service.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryGetServiceRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    Date date;
    @NotNull
    @Email
    private String userEmail;

    public static DiaryGetServiceRequest newInstance(Date date, String userEmail) {
        return new DiaryGetServiceRequest(date, userEmail);
    }
}
