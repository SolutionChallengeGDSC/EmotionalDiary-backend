package com.gdsc.EmotionalDiary.service.diary;

import com.gdsc.EmotionalDiary.domain.diary.Diary;
import com.gdsc.EmotionalDiary.domain.diary.DiaryRepository;
import com.gdsc.EmotionalDiary.domain.todo.Todo;
import com.gdsc.EmotionalDiary.domain.user.User;
import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.NoDataException;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DiaryServiceResponse;
import com.gdsc.EmotionalDiary.service.todo.dto.respone.TodoServiceResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final DiaryServiceResponse saveDiary(@Valid final DiaryServiceRequest diaryServiceRequest) {
        logger.info("일기 생성");
        User user = userRepository.findByEmail(diaryServiceRequest.getUserEmail()).orElseThrow(
                () -> new NoDataException("유저가 존재하지 않습니다."));

        Diary diary = diaryRepository.save(Diary.newInstance(
                diaryServiceRequest.getTitle(),
                diaryServiceRequest.getContent(),
                user
        ));
        return convertDiaryResponse(diary);
    }

    private DiaryServiceResponse convertDiaryResponse(Diary diary) {
        return DiaryServiceResponse.of(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                diary.isIsprivate(),
                diary.getCreatedAt()
        );
    }
}