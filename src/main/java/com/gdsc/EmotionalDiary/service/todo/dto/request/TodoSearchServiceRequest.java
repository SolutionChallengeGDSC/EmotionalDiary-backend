package com.gdsc.EmotionalDiary.service.todo.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodoSearchServiceRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @NotNull
    LocalDateTime startTime;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime endTime;
    @Email
    @NotNull
    String userEmail;
    List<String> categories;
    @NotNull
    int start;
    @NotNull
    int limit;
    @NotNull
    boolean reverse;

    public static TodoSearchServiceRequest newInstance(LocalDateTime startTime, LocalDateTime endTime, String userEmail, List<String> categories,
                                                       int start, int limit, boolean reverse){
        return new TodoSearchServiceRequest(startTime, endTime, userEmail, categories, start, limit, reverse);
    }
}
