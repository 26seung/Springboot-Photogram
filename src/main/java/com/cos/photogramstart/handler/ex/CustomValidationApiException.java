package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException{

    // 객체를 구분할 때 사용
    public static final long serialVersionUID = 1L;

    private Map<String ,String >errMap;

    public CustomValidationApiException(String message){
        super(message);
    }
    public CustomValidationApiException(String message, Map<String,String>errMap){
        super(message);
        this.errMap = errMap;
    }

    public Map<String,String> getErrMap(){
        return errMap;
    }
}
