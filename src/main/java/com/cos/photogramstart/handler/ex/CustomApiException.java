package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{

    // 객체를 구분할 때 사용
    public static final long serialVersionUID = 1L;

    public CustomApiException(String message){
        super(message);
    }
}