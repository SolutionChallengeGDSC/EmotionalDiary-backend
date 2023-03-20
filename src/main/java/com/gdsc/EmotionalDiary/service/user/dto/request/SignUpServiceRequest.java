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
    private String picture;

    public static final SignUpServiceRequest newInstance(String email, String nickname, String picture) {
        return new SignUpServiceRequest(email, nickname, picture);
    }

}
