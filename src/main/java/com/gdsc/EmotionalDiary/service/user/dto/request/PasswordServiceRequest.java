package com.gdsc.EmotionalDiary.service.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordServiceRequest {
    private Long id;
    private String password;

    public static PasswordServiceRequest newInstance(Long id, String password) {
        return new PasswordServiceRequest(id, password);
    }
}
