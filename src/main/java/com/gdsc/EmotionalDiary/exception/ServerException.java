package com.gdsc.EmotionalDiary.exception;

public class ServerException extends RuntimeException{
    private String message = "서버 에러 발생.";

    public ServerException(String message) {
        super(message);
        this.message = message;
    }
}
