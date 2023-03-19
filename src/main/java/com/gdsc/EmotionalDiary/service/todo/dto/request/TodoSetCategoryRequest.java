package com.gdsc.EmotionalDiary.service.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoSetCategoryRequest {
    @NotNull
    Long id;
    @NotNull
    @Length(min = 2, max = 100)
    String category;

    public static TodoSetCategoryRequest newInstance(Long id, String category) {
        return new TodoSetCategoryRequest(id, category);
    }
}
