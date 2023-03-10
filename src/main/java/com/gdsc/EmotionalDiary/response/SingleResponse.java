package com.gdsc.EmotionalDiary.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
@Getter
@SuperBuilder
public class SingleResponse<T> extends CommonResponse{
    T result;
}
