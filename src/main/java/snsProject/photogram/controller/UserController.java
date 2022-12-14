package snsProject.photogram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.user.UserListDto;
import snsProject.photogram.dto.user.UserProfileDto;
import snsProject.photogram.dto.user.UserSearchDto;
import snsProject.photogram.service.UserService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/user/search")
    public String userList(UserSearchDto userSearchDto, Model model) {
        List<UserListDto> userListDtos = userService.userList(userSearchDto.getUsername(), userSearchDto.getGender());
        model.addAttribute("userListDtos", userListDtos);
        return "user/userList";
    }

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        UserProfileDto dto = userService.userProfile(id,principalDetails);
        model.addAttribute("dto", dto);

        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        model.addAttribute("principal", principalDetails.getUser());
        return "user/update";
    }
}
