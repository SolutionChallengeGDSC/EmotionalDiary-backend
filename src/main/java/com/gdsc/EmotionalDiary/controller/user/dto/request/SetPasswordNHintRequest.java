package com.gdsc.EmotionalDiary.controller.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SetPasswordNHintRequest {
    @NotNull
    @Length(min = 2, max = 50)
    String password;
    @NotNull
    @Length(min = 2, max = 50)
    String hint;
}
