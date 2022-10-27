package snsProject.photogram.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.dto.CMRespDto;
import snsProject.photogram.dto.user.UserRespDto;
import snsProject.photogram.dto.user.UserUpdateDto;
import snsProject.photogram.handler.exception.CustomValidationApiException;
import snsProject.photogram.handler.exception.CustomValidationException;
import snsProject.photogram.service.UserService;

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
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
//                log.info("에러 메시지 : "+ error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        }

        UserRespDto userRespDto = userService.updateUser(id, userUpdateDto.toEntity());
        principalDetails.setUser(userRespDto.getUser());
        return new CMRespDto<>(1, "회원수정 완료", userRespDto);
    }
}
