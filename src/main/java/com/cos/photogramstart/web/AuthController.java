package com.cos.photogramstart.web;

import com.cos.photogramstart.web.dto.auth.SignupDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //  1.IOC , 2.파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger( AuthController.class );

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }
    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto){
        log.info(signupDto.toString());
        return "auth/signin";
    }
}
