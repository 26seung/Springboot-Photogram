package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor        // final 필드 DI 할때 사용
@Controller //  1.IOC , 2.파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger( AuthController.class );

//    @Autowired
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }
    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            Map<String, String >errMap = new HashMap<>();

            for(FieldError error:bindingResult.getFieldErrors()){
                errMap.put(error.getField(),error.getDefaultMessage());
                System.out.println("bindingResult: " + error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성검사 실패함", errMap);
        }else {
        log.info(signupDto.toString());
        User user = signupDto.toEntity();
        log.info(user.toString());

        User userEntity = authService.회원가입(user);
        log.info(userEntity.toString());

        return "auth/signin";

        }

    }
}
