package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@ControllerAdvice       //  모든 Exception 을 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public String validationException(CustomValidationException e){

        // CMrespDto, Script 비교
        // 1. 클라이언트에게 응답할 때는 Script 가 좋음
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 - CMRespDto
        return Script.back(e.getErrMap().toString());
    }
}
