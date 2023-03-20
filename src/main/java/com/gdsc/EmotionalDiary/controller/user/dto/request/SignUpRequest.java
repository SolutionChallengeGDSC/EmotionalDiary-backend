package com.gdsc.EmotionalDiary.controller.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @NotNull
    @Email
    @Length(min = 2, max = 50)
    String email;
    @NotNull
    @Length(min = 2, max = 30)
    String nickname;
    @NotNull
    @Length(min = 2)
    String picture;
}
