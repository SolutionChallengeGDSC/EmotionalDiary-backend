package com.gdsc.EmotionalDiary.controller.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryGetRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    LocalDate createdAt;
    @NotNull
    @Email
    String userEmail;
}
