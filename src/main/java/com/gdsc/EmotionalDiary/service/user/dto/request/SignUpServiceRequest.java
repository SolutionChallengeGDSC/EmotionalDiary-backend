package com.gdsc.EmotionalDiary.service.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpServiceRequest {
    private String email;
    private String nickname;

    public static final SignUpServiceRequest newInstance(String email, String nickname) {
        return new SignUpServiceRequest(email, nickname);
    }

}
