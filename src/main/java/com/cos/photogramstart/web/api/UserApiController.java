package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,            //  꼭 @Valid 가 적혀있는 다음 파라미터에 적어야 함
            @AuthenticationPrincipal PrincipalDetails principalDetails){


        if(bindingResult.hasErrors()){
            Map<String, String > errMap = new HashMap<>();

            for(FieldError error:bindingResult.getFieldErrors()){
                errMap.put(error.getField(),error.getDefaultMessage());
                System.out.println("bindingResult: " + error.getDefaultMessage());
                System.out.println("errMap : " + errMap);
            }
            throw new CustomValidationApiException("유효성검사 실패함", errMap);
        }else {
            System.out.println("userUpdateDto: "+ userUpdateDto);
            System.out.println("principalDetails : " + principalDetails);
            User userEntity = userService.회원수정(id,userUpdateDto.toEntity());
            // 세션 정보를 업데이트 해주어야 정보 반영이 됨
            principalDetails.setUser(userEntity);
            return new CMRespDto<>(1,"회원수정완료",userEntity);
        }
    }
}
