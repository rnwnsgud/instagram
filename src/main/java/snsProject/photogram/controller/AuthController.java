package snsProject.photogram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.auth.SignupDto;
import snsProject.photogram.service.AuthService;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signup")
    public String signUpPage() {
        return "auth/signup";
    }

    @PostMapping("/auth/signup")
    public String singUp(@Valid SignupDto signupDto) {
        User user = signupDto.toEntity();
        authService.signUp(user);

        return "auth/signin";
    }

    @GetMapping("/auth/signin")
    public String signInPage() {
        return "auth/signin";
    }
}
