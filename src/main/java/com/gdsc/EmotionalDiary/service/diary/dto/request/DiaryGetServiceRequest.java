package com.gdsc.EmotionalDiary.service.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiaryGetServiceRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    LocalDate createdAt;
    @NotNull
    @Email
    private String userEmail;

    public static DiaryGetServiceRequest newInstance(LocalDate createdAt, String userEmail) {
        return new DiaryGetServiceRequest(createdAt, userEmail);
    }
}
