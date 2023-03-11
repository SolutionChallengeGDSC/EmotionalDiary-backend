package com.gdsc.EmotionalDiary.service.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpSuccessResponse {
    private String email;
    private String nickname;

    public static SignUpSuccessResponse of(String email, String nickname) {
        return new SignUpSuccessResponse(email, nickname);
    }
}
