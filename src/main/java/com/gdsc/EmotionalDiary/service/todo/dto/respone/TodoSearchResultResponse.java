package com.gdsc.EmotionalDiary.service.todo.dto.respone;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoSearchResultResponse {
    boolean hasNext;
    int size;
    List<TodoServiceResponse> todos;
    public static TodoSearchResultResponse newInstance(boolean hasNext, int size, List<TodoServiceResponse> todos) {
        return new TodoSearchResultResponse(hasNext, size, todos);
    }
}
