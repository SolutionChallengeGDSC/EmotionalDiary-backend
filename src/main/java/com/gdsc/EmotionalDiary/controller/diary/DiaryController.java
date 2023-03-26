package com.gdsc.EmotionalDiary.controller.diary;

import com.gdsc.EmotionalDiary.controller.diary.dto.request.DiaryCreateRequest;
import com.gdsc.EmotionalDiary.response.CommonResponse;
import com.gdsc.EmotionalDiary.response.SingleResponse;
import com.gdsc.EmotionalDiary.service.diary.DiaryService;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DiaryServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public final CommonResponse createDiary(@RequestBody @Valid final DiaryCreateRequest diaryMakeRequest) {
        return SingleResponse.<DiaryServiceResponse>builder()
                .success(true)
                .status(200)
                .message("일기 작성 성공")
                .result(diaryService.saveDiary(DiaryServiceRequest.newInstance(
                        diaryMakeRequest.getTitle(),
                        diaryMakeRequest.getContent(),
                        diaryMakeRequest.getUserEmail()
                )))
                .build();
    }
}
