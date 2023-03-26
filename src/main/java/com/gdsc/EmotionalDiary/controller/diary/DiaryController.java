package com.gdsc.EmotionalDiary.controller.diary;

import com.gdsc.EmotionalDiary.controller.diary.dto.request.DiaryCreateRequest;
import com.gdsc.EmotionalDiary.controller.diary.dto.request.DiaryGetRequest;
import com.gdsc.EmotionalDiary.response.CommonResponse;
import com.gdsc.EmotionalDiary.response.SingleResponse;
import com.gdsc.EmotionalDiary.service.diary.DiaryService;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryGetServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiaryServiceRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.request.DiarySetRequest;
import com.gdsc.EmotionalDiary.service.diary.dto.response.DiaryServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryService diaryService;

    @PostMapping
    public final CommonResponse createDiary(@RequestBody @Valid final DiaryCreateRequest diaryCreateRequest) {
        return SingleResponse.<DiaryServiceResponse>builder()
                .success(true)
                .status(200)
                .message("일기 작성 성공")
                .result(diaryService.saveDiary(DiaryServiceRequest.newInstance(
                        diaryCreateRequest.getTitle(),
                        diaryCreateRequest.getContent(),
                        diaryCreateRequest.getPrivateStatus(),
                        diaryCreateRequest.getUserEmail()
                )))
                .build();
    }

    @GetMapping
    public final CommonResponse getDiaries(@RequestBody @Valid final DiaryGetRequest diaryGetRequest) {
        return SingleResponse.<DiaryServiceResponse>builder()
                .success(true)
                .status(200)
                .message("일기 목록 가져오기 성공")
                .result(diaryService.getDiaries(DiaryGetServiceRequest.newInstance(
                        diaryGetRequest.getCreatedAt(),
                        diaryGetRequest.getUserEmail()
                )))
                .build();
    }

    @GetMapping("/{id}")
    public final CommonResponse getDiaray(@PathVariable(name = "id") final Long id) {
        return SingleResponse.<DiaryServiceResponse>builder()
                .success(true)
                .status(200)
                .message("일기 가져오기 성공")
                .result(diaryService.getDiary(id))
                .build();
    }

    @PutMapping("/{id}")
    public final CommonResponse updateDiary(@PathVariable final Long id, @RequestBody @Valid final DiaryCreateRequest diaryCreateRequest) {
        return SingleResponse.<DiaryServiceResponse>builder()
                .success(true)
                .status(200)
                .message("일기 수정 성공")
                .result(diaryService.updateDiary(DiarySetRequest.newInstance(
                        id,
                        diaryCreateRequest.getTitle(),
                        diaryCreateRequest.getContent(),
                        diaryCreateRequest.getPrivateStatus(),
                        diaryCreateRequest.getUserEmail()
                )))
                .build();
    }

    @DeleteMapping("/{id}")
    public final CommonResponse deleteDiary(@PathVariable final Long id) {
        return SingleResponse.builder()
                .success(true)
                .status(200)
                .message("일기 삭제 완료")
                .result(diaryService.deleteDiary(id))
                .build();
    }
}
