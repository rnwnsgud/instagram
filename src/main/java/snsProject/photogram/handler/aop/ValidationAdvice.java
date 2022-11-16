package snsProject.photogram.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import snsProject.photogram.handler.exception.CustomValidationApiException;
import snsProject.photogram.handler.exception.CustomValidationException;

import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class ValidationAdvice { // Advice : 공통기능

    @Around(("execution(* snsProject.photogram.controller.api.*Controller.*(..))"))
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                System.out.println("API 유효성 검사를 하는 함수입니다.");
                BindingResult bindingResult = (BindingResult) arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationApiException("유효성검사 실패", errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }

    @Around(("execution(* snsProject.photogram.controller.*Controller.*(..))"))
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {

            if (arg instanceof BindingResult) {
                System.out.println("유효성 검사를 하는 함수입니다.");
                BindingResult bindingResult = (BindingResult)arg;
                if (bindingResult.hasErrors()) {
                    Map<String, String> errorMap = new HashMap<>();

                    for (FieldError error : bindingResult.getFieldErrors()) {
                        errorMap.put(error.getField(), error.getDefaultMessage());
                    }
                    throw new CustomValidationException("유효성 검사 실패", errorMap);
                }
            }
        }

        return proceedingJoinPoint.proceed();
    }
}
