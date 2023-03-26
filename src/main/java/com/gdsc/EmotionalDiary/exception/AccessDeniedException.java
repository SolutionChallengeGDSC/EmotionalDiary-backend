package com.gdsc.EmotionalDiary.exception;

public class AccessDeniedException extends RuntimeException{
    private String message = "승인되지 않은 접근입니다.";

    public AccessDeniedException(String message) {
        super(message);
        this.message = message;
    }
}
