package snsProject.photogram.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.dto.CMRespDto;
import snsProject.photogram.dto.subscribe.SubscribeDto;
import snsProject.photogram.dto.user.UserRespDto;
import snsProject.photogram.dto.user.UserUpdateDto;
import snsProject.photogram.handler.exception.CustomValidationApiException;
import snsProject.photogram.handler.exception.CustomValidationException;
import snsProject.photogram.service.SubscribeService;
import snsProject.photogram.service.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;
    private final SubscribeService subscribeService;

    @GetMapping("/api/user/{pageUserId}/subscribe")
    public ResponseEntity<?> subscribeList(
            @PathVariable int pageUserId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<SubscribeDto> subscribeDto = subscribeService.getSubscribeList(principalDetails.getUser().getId(), pageUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "구독자 정보 리스트 불러오기", subscribeDto), HttpStatus.OK);
    }

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        UserRespDto userRespDto = userService.updateUser(id, userUpdateDto.toEntity());
        principalDetails.setUser(userRespDto.getUser());
        return new CMRespDto<>(1, "회원수정 완료", userRespDto);
    }
}
