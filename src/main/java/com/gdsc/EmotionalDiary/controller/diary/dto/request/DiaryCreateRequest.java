package com.gdsc.EmotionalDiary.controller.diary.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


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
    Boolean privateStatus;
    @NotNull
    @Email
    String userEmail;
}
