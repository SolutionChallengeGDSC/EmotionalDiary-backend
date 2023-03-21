package com.gdsc.EmotionalDiary.service.user.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PasswordAndHintServiceRequest {
    private Long id;
    private String password;
    private String hint;

    public static PasswordAndHintServiceRequest newInstance(Long id, String password, String hint) {
        return new PasswordAndHintServiceRequest(id, password, hint);
    }
}
