package com.gdsc.EmotionalDiary.controller.diary.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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
    @Email
    String userEmail;
}
