package com.gdsc.EmotionalDiary.service.user;

import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.DuplicateUserException;
import com.gdsc.EmotionalDiary.service.user.dto.request.SignUpServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.response.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserServiceTest {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @DisplayName("유저 생성 테스트")
    @Test
    void saveUser() {
        // given
        SignUpServiceRequest request = SignUpServiceRequest.newInstance("dongwon0103@naver.com", "닉네임");

        // when
        UserResponse response = userService.saveUser(request);

        // then
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getNickname(), response.getNickname());
    }

    @DisplayName("유저 이메일 중복 테스트")
    @Test
    void duplicateUserEmail() {
        // given
        String email = "dongwon0103@naver.com";

        // when
        userService.saveUser(SignUpServiceRequest.newInstance(email, "temp_nickname"));

        // then
        assertThrows(DuplicateUserException.class, () -> userService.duplicateUserEmail(email));
    }
}