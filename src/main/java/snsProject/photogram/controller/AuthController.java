package snsProject.photogram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.auth.SignupDto;
import snsProject.photogram.handler.exception.CustomValidationException;
import snsProject.photogram.service.AuthService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signup")
    public String signUpPage() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String signUp(@Valid SignupDto signupDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
//                log.info("에러 메시지 : "+ error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        }

        User user = signupDto.toEntity();
        authService.signUp(user);

        return "auth/signin";
    }

    @GetMapping("/auth/signin")
    public String signInPage() {
        return "auth/signin";
    }
}
