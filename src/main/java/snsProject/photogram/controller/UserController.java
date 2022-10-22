package snsProject.photogram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import snsProject.photogram.config.auth.PrincipalDetails;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("어노테이션으로 찾은 세션 정보 : " + principalDetails.getUser());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
        log.info("직접 찾은 세션 정보 : " + mPrincipalDetails.getUser());
        return "user/update";
    }
}
