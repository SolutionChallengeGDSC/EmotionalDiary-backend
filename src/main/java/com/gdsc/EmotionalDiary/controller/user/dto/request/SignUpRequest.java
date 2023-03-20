package com.gdsc.EmotionalDiary.controller.user.dto.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    @Email
    @Length(min = 2, max = 50)
    String email;
    @Length(min = 2, max = 30)
    String nickname;
    @Length(min = 2)
    String picture;
}
