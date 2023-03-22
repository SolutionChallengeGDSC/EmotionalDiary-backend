package com.gdsc.EmotionalDiary.controller.user;

import com.gdsc.EmotionalDiary.controller.user.dto.request.PasswordRequest;
import com.gdsc.EmotionalDiary.controller.user.dto.request.SetPasswordNHintRequest;
import com.gdsc.EmotionalDiary.controller.user.dto.request.SignUpRequest;
import com.gdsc.EmotionalDiary.response.CommonResponse;
import com.gdsc.EmotionalDiary.response.SingleResponse;
import com.gdsc.EmotionalDiary.service.user.UserService;
import com.gdsc.EmotionalDiary.service.user.dto.request.PasswordAndHintServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.request.PasswordServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.request.SignUpServiceRequest;
import com.gdsc.EmotionalDiary.service.user.dto.response.UserResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.websocket.server.PathParam;
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

    @DeleteMapping("{id}")
    public final CommonResponse deleteUser(@PathVariable(name = "id") final Long id) {
        return SingleResponse.<String>builder()
                .success(true)
                .status(200)
                .message("유저 삭제")
                .result(userService.deleteUserByEmail(id))
                .build();
    }

    @GetMapping("{id}")
    public final CommonResponse findUserById(@PathVariable(name = "id") final Long id) {
        return SingleResponse.<UserResponse>builder()
                .success(true)
                .status(200)
                .message("유저 조회 성공")
                .result(userService.findById(id))
                .build();
    }

    @GetMapping("{id}/hint")
    public final CommonResponse getHint(@PathVariable(name = "id") final Long id) {
        return SingleResponse.<String>builder()
                .success(true)
                .status(200)
                .message("힌트 가져오기 성공")
                .result(userService.getHint(id))
                .build();
    }

    @PostMapping("{id}/pass-hint")
    public final CommonResponse setPasswordAndHint(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final SetPasswordNHintRequest setPasswordNHintRequest) {
        return SingleResponse.<String>builder()
                .success(true)
                .status(200)
                .message("패스워드와 힌트 세팅 성공")
                .result(userService.setUserPasswordAndHint(
                        PasswordAndHintServiceRequest.newInstance(
                                id,
                                setPasswordNHintRequest.getPassword(),
                                setPasswordNHintRequest.getHint()
                        )
                ))
                .build();
    }

    @PostMapping("{id}/password")
    public final CommonResponse passwordMatch(@PathVariable(name = "id") final Long id,
                                              @RequestBody @Valid PasswordRequest passwordRequest) {
        return SingleResponse.builder()
                .success(true)
                .status(200)
                .message("패스워드 매치 결과 조회")
                .result(userService.passwordMatch(PasswordServiceRequest.newInstance(
                        id,
                        passwordRequest.getPassword()
                )))
                .build();
    }

    @GetMapping
    public final CommonResponse getUserByEmail(@RequestParam(name = "email") final String email) {
        return SingleResponse.<UserResponse>builder()
                .success(true)
                .status(200)
                .message("유저 이메일로 조회 성공(없어도 성공)")
                .result(userService.findByEmail(email))
                .build();
    }
}
