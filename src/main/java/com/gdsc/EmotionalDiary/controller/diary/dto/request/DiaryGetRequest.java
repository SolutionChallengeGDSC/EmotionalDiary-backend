package com.gdsc.EmotionalDiary.controller.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryGetRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    Date date;
    @NotNull
    @Email
    String userEmail;
}
