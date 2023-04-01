package com.gdsc.EmotionalDiary.service.diary;

import com.gdsc.EmotionalDiary.domain.diary.Diary;
import com.gdsc.EmotionalDiary.domain.diary.DiaryRepository;
import com.gdsc.EmotionalDiary.domain.user.User;
import com.gdsc.EmotionalDiary.domain.user.UserRepository;
import com.gdsc.EmotionalDiary.exception.NoDataException;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryGetServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiarySetRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DairyDateServiceResponse;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DiaryGetServiceResponse;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DiaryServiceResponse;
import com.gdsc.EmotionalDiary.util.PredictModule;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                diaryServiceRequest.getDate(),
                diaryServiceRequest.getScore(),
                user
        ));

        PredictModule predictModule = PredictModule.newInstance(user.getId());
        predictModule.saveDiaryContentAndPredict(diary.getId(), diary.getContent());
        return convertDiaryResponse(diary);
    }

    public final DiaryGetServiceResponse getDiaries(@Valid final DiaryGetServiceRequest diaryGetServiceRequest) {
        logger.info("일기 목록");
        List<Diary> diaries = diaryRepository.findByDate(diaryGetServiceRequest.getDate());
        return DiaryGetServiceResponse.newInstance(
                diaries.stream().map(diary -> convertDiaryResponse(diary)).collect(Collectors.toList())
        );
    }

    public final DiaryServiceResponse getDiary(final Long id) {
        logger.info("일기 가져오기");

        return convertDiaryResponse(diaryRepository.findById(id).orElseThrow(
                () -> new NoDataException("일기가 존재하지 않습니다.")
        ));
    }

    public final DiaryServiceResponse updateDiary(@Valid final DiarySetRequest diarySetRequest) {
        Diary diary = diaryRepository.findById(diarySetRequest.getId()).orElseThrow(() -> new NoDataException("일기가 존재하지 않습니다."));
        diary.setTitle(diarySetRequest.getTitle());
        diary.setContent(diarySetRequest.getContent());
        diary.setDate(diarySetRequest.getDate());
        diary.setScore(diarySetRequest.getScore());

        Diary modifyDiary = diaryRepository.save(diary);
        return convertDiaryResponse(modifyDiary);
    }

    public final boolean deleteDiary(final Long id) {
        diaryRepository.deleteById(id);
        return true;
    }

    private DiaryServiceResponse convertDiaryResponse(Diary diary) {
        return DiaryServiceResponse.of(
                diary.getId(),
                diary.getTitle(),
                diary.getContent(),
                diary.getDate(),
                diary.getScore()
        );
    }

    public final DairyDateServiceResponse getDate() {
        logger.info("일기 작성된 날짜 목록 가져오기");

        List<Diary> diaries = diaryRepository.findAll();
        return DairyDateServiceResponse.newInstance(
                diaries.stream().map(diary -> diary.getDate()).collect(Collectors.toList())
        );
    }
}
