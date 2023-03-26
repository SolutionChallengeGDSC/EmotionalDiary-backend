package com.gdsc.EmotionalDiary.controller.todo;

import com.gdsc.EmotionalDiary.controller.todo.dto.request.TodoCategoryRequest;
import com.gdsc.EmotionalDiary.controller.todo.dto.request.TodoGoalRequest;
import com.gdsc.EmotionalDiary.controller.todo.dto.request.TodoMakeRequest;
import com.gdsc.EmotionalDiary.controller.todo.dto.request.TodoSearchRequest;
import com.gdsc.EmotionalDiary.response.CommonResponse;
import com.gdsc.EmotionalDiary.response.SingleResponse;
import com.gdsc.EmotionalDiary.service.todo.TodoService;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSearchServiceRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoServiceRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSetCategoryRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.request.TodoSetGoalRequest;
import com.gdsc.EmotionalDiary.service.todo.dto.respone.TodoSearchResultResponse;
import com.gdsc.EmotionalDiary.service.todo.dto.respone.TodoServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping
    public final CommonResponse makeTodo(@RequestBody @Valid final TodoMakeRequest todoMakeRequest) {
        return SingleResponse.<TodoServiceResponse>builder()
                .success(true)
                .status(200)
                .message("투두 만들기 성공")
                .result(todoService.saveTodo(TodoServiceRequest.newInstance(
                        todoMakeRequest.getGoal(),
                        todoMakeRequest.getCategory(),
                        todoMakeRequest.getUserEmail(),
                        todoMakeRequest.getGoalTime()
                )))
                .build();
    }

    @PutMapping("/success/{id}")
    public final CommonResponse changeSuccess(@PathVariable final Long id) {
        return SingleResponse.<TodoServiceResponse>builder()
                .success(true)
                .status(200)
                .message("투두 성공 상태 변경")
                .result(todoService.changeSuccessStatus(id))
                .build();
    }

    @PutMapping("/goal/{id}")
    public final CommonResponse changeGoal(@PathVariable final Long id, @RequestBody @Valid final TodoGoalRequest todoGoalRequest) {
        return SingleResponse.<TodoServiceResponse>builder()
                .success(true)
                .status(200)
                .message("투두 목표 변경")
                .result(todoService.changeGoal(TodoSetGoalRequest.newInstance(
                    id, todoGoalRequest.getGoal()
                )))
                .build();
    }

    @PutMapping("/category/{id}")
    public final CommonResponse changeCategory(@PathVariable final Long id, @RequestBody @Valid final TodoCategoryRequest todoCategoryRequest) {
        return SingleResponse.<TodoServiceResponse>builder()
                .success(true)
                .status(200)
                .message("투두 카테고리 변경")
                .result(todoService.changeCategory(TodoSetCategoryRequest.newInstance(
                        id, todoCategoryRequest.getCategory()
                )))
                .build();
    }

    @DeleteMapping("/{id}")
    public final CommonResponse deleteTodo(@PathVariable final Long id) {
        return SingleResponse.builder()
                .success(true)
                .status(200)
                .message("투두 삭제 완료")
                .result(todoService.deleteTodo(id))
                .build();
    }

    @PostMapping("/search")
    public final CommonResponse getTodos(
            @RequestParam(name = "start") final int start,
            @RequestParam(name = "limit", required = false, defaultValue = "5") final int limit,
            @RequestParam(name = "reverse", required = false, defaultValue = "false") final boolean reverse,
            @RequestBody @Valid final TodoSearchRequest todoSearchRequest) {

        return SingleResponse.<TodoSearchResultResponse>builder()
                .success(true)
                .status(200)
                .message("투두 데이터 가져오기 완료")
                .result(todoService.getTodos(TodoSearchServiceRequest.newInstance(
                        todoSearchRequest.getStartTime(),
                        todoSearchRequest.getEndTime(),
                        todoSearchRequest.getUserEmail(),
                        todoSearchRequest.getCategories(),
                        start,
                        limit,
                        reverse)))
                .build();
    }
}
