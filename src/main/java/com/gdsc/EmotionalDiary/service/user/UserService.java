package com.gdsc.EmotionalDiary.service.user;

import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
