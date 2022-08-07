package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model){
        User userEntity = userService.회원프로필(id);
        model.addAttribute("user",userEntity);
        return "user/profile";
    }
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails){
        // 1. 어노테이션 PrincipalDetails 을 사용하는 것이 유용하다.
        System.out.println("세션 정보: " + principalDetails.getUser());

        // 2. 직접 세션 정보를 찾으려면 다음과 같은 방식으로 찾아 볼 수 있다.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("직접 찾은 세션 정보: " + auth.getPrincipal());

        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        System.out.println("직접 찾은 세션 정보 2: "+ mPrincipalDetails.getUser());

//        model.addAttribute("principal",principalDetails.getUser());       //  security tag 사용하면 model 사용 필요없음
        return "user/update";
    }
}
