package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice       //  모든 Exception 을 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public CMRespDto<Map<String,String>> validationException(CustomValidationException e){
        return new CMRespDto(-1,e.getMessage(), e.getErrMap());
    }
}
