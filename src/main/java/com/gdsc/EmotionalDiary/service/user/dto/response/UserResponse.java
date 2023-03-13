package com.gdsc.EmotionalDiary.service.user.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private String email;
    private String nickname;
    private String picture;

    public static UserResponse of(String email, String nickname, String picture) {
        return new UserResponse(email, nickname, picture);
    }
}
