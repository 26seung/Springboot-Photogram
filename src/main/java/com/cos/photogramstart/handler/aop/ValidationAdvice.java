package com.cos.photogramstart.handler.aop;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component      //      restController. Service 등 모든 것들이 component ㅇ를 사속해서 만들어져 있음
@Aspect
public class ValidationAdvice {

    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web api Aspect =====================================");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg:args){
//            System.out.println("arg : " + arg);
            //  BindingResult 가 있는 로직
            if(arg instanceof BindingResult){
                System.out.println("유효성 검사를 하는 함수입니다." + arg);
                BindingResult bindingResult = (BindingResult) arg;

                //

                if(bindingResult.hasErrors()){
                    Map<String, String > errMap = new HashMap<>();

                    for(FieldError error:bindingResult.getFieldErrors()){
                        errMap.put(error.getField(),error.getDefaultMessage());
                    }
                    // throw 날리는 순간 밑의 코드는 동작하지 않음
                    throw new CustomValidationApiException("유효성검사 실패함", errMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web Aspect =====================================");
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg:args){
            System.out.println("arg : " + arg);
            if(arg instanceof BindingResult){
                BindingResult bindingResult = (BindingResult) arg;

                //

                if(bindingResult.hasErrors()){
                    Map<String, String > errMap = new HashMap<>();

                    for(FieldError error:bindingResult.getFieldErrors()){
                        errMap.put(error.getField(),error.getDefaultMessage());
                    }
                    // throw 날리는 순간 밑의 코드는 동작하지 않음
                    throw new CustomValidationException("유효성검사 실패함", errMap);
                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
