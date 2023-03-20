package com.gdsc.EmotionalDiary.controller.user;

import com.gdsc.EmotionalDiary.controller.user.dto.request.SignUpRequest;
import com.gdsc.EmotionalDiary.response.CommonResponse;
import com.gdsc.EmotionalDiary.response.SingleResponse;
import com.gdsc.EmotionalDiary.service.user.UserService;
import com.gdsc.EmotionalDiary.service.user.dto.request.SignUpServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/sign-up")
    public final CommonResponse signUp(@RequestBody @Valid final SignUpRequest signUpRequest) {
        logger.info("/user/sign-up 호출");
        userService.duplicateUserEmail(signUpRequest.getEmail());
        
        return SingleResponse.<UserResponse>builder()
                .success(true)
                .status(200)
                .message("유저 DB 데이터 생성")
                .result(
                        userService.saveUser(SignUpServiceRequest.newInstance(
                                signUpRequest.getEmail(),
                                signUpRequest.getNickname(),
                                signUpRequest.getPicture())))
                .build();
    }

    @DeleteMapping("{email}")
    public final CommonResponse deleteUser(@PathVariable final String email) {
        return SingleResponse.<String>builder()
                .success(true)
                .status(200)
                .message("유저 삭제")
                .result(userService.deleteUserByEmail(email))
                .build();
    }

    @GetMapping("{email}")
    public final CommonResponse findUserByEmail(@PathVariable final String email) {
        return SingleResponse.<UserResponse>builder()
                .success(true)
                .status(200)
                .message("유저 조회 성공")
                .result(userService.findByEmail(email))
                .build();
    }
}
