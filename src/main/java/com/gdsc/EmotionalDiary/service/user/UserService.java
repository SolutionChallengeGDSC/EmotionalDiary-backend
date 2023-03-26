package com.gdsc.EmotionalDiary.service.user;

import com.gdsc.EmotionalDiary.domain.user.Role;
import com.gdsc.EmotionalDiary.domain.user.User;
import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.DuplicateUserException;
import com.gdsc.EmotionalDiary.exception.NoDataException;
import com.gdsc.EmotionalDiary.service.user.dto.request.PasswordAndHintServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.request.PasswordServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.request.SignUpServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.response.UserResponse;
import com.gdsc.EmotionalDiary.util.PredictModule;
import com.gdsc.EmotionalDiary.util.json.RecommendJson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final UserResponse saveUser(final SignUpServiceRequest signUpServiceRequest) {
        duplicateUserEmail(signUpServiceRequest.getEmail());

        User successSaveUser = userRepository.save(User.newInstance(
                signUpServiceRequest.getEmail(),
                signUpServiceRequest.getNickname(),
                signUpServiceRequest.getPicture(),
                Role.USER
        ));

        return UserResponse.of(
                successSaveUser.getId(),
                successSaveUser.getEmail(),
                successSaveUser.getNickname(),
                successSaveUser.getPicture()
        );
    }

    public final UserResponse findById(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        return UserResponse.of(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getPicture()
        );
    }

    public final String deleteUserByEmail(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        String email = user.getEmail();
        userRepository.deleteById(id);
        return email;
    }

    public final void duplicateUserEmail(final String email) {
        userRepository.findByEmail(email).ifPresent( user -> {
                    logger.error("Duplicate User Exception을 발생");
                    throw new DuplicateUserException("같은 이메일의 유저가 존재합니다.");
        });
    }

    public final String setUserPasswordAndHint(final PasswordAndHintServiceRequest passwordAndHintServiceRequest) {
        User user = userRepository.findById(passwordAndHintServiceRequest.getId()).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        user.setPasswordAndHint(passwordAndHintServiceRequest.getPassword(), passwordAndHintServiceRequest.getHint());
        userRepository.save(user);
        return user.getPasswordHint();
    }
    public final boolean passwordMatch(final PasswordServiceRequest passwordServiceRequest) {
        User user = userRepository.findById(passwordServiceRequest.getId()).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        if(user.getDiaryPassword() == null) {
            throw new NoDataException("유저 비밀번호가 없음");
        }
        if(user.getDiaryPassword().equals(passwordServiceRequest.getPassword())) {
            return true;
        }
        return false;
    }

    public final String getHint(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        if(user.getPasswordHint() == null) {
            throw new NoDataException("유저 힌트가 없음");
        }
        return user.getPasswordHint();
    }

    public final UserResponse findByEmail(final String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        return UserResponse.of(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getPicture());
    }
    public final RecommendJson userRecommend(final Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoDataException("유저가 존재하지 않음"));
        PredictModule predictModule = PredictModule.newInstance(user.getId());
        try {
            return predictModule.getRecommand();
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
