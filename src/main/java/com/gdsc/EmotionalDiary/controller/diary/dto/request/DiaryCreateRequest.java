package com.gdsc.EmotionalDiary.controller.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryCreateRequest {
    @NotNull
    @Length(min = 1, max = 28)
    String title;
    @NotNull
    String content;
    @NotNull
    Date date;
    @NotNull
    @Email
    String userEmail;
}
