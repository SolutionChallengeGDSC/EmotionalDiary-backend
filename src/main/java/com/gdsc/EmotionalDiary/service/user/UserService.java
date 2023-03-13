package com.gdsc.EmotionalDiary.service.user;

import com.gdsc.EmotionalDiary.domain.user.Role;
import com.gdsc.EmotionalDiary.domain.user.User;
import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.DuplicateUserException;
import com.gdsc.EmotionalDiary.service.user.dto.request.SignUpServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final UserResponse saveUser(final SignUpServiceRequest signUpServiceRequest) {
        User successSaveUser = userRepository.save(User.newInstance(
                signUpServiceRequest.getEmail(),
                signUpServiceRequest.getNickname(),
                Role.USER
        ));

        return UserResponse.of(
                successSaveUser.getEmail(),
                successSaveUser.getNickname(),
                successSaveUser.getPicture()
        );
    }

    public final void duplicateUserEmail(final String email) {
        userRepository.findByEmail(email).ifPresent( user -> {
                    logger.error("Duplicate User Exception을 발생");
                    throw new DuplicateUserException("같은 이메일의 유저가 존재합니다.");
        });
    }
}
