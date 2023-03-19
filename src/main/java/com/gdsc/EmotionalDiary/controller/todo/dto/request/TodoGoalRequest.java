package com.gdsc.EmotionalDiary.controller.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TodoGoalRequest {
    @NotNull
    @Length(min=1,max = 50)
    String goal;
}
