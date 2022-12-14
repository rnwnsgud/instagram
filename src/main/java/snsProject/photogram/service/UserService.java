package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.config.auth.PrincipalDetails;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.user.UserListDto;
import snsProject.photogram.dto.user.UserProfileDto;
import snsProject.photogram.dto.user.UserRespDto;
import snsProject.photogram.handler.exception.CustomException;
import snsProject.photogram.handler.exception.CustomValidationApiException;
import snsProject.photogram.repository.SubscribeRepository;
import snsProject.photogram.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public List<UserListDto> userList(String username, String gender) {
        List<UserListDto> userWithSearch = userRepository.findUserWithSearch(username, gender);
        return userWithSearch;
    }

    @Transactional(readOnly = true)
    public UserProfileDto userProfile(int userId, PrincipalDetails principalDetails) {
        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        userEntity.getImages().forEach(image -> {
            image.setLikeCount(image.getLikes().size());
        });

        int subscribeState = subscribeRepository.subscribeState(principalDetails.getUser().getId(), userId);
        int subscribeCount = subscribeRepository.subscribeCount(userId);

        UserProfileDto dto = UserProfileDto.builder()
                .pageOwnerState(userId == principalDetails.getUser().getId() ? true : false)
                .user(userEntity)
                .imageCount(userEntity.getImages().size())
                .subscribeState(1 == subscribeState)
                .subscribeCount(subscribeCount)
                .build();
        return dto;

    }

    public UserRespDto updateUser(int id, User user) {
        User userEntity = userRepository.findById(10).orElseThrow(() -> {
            return new CustomValidationApiException("찾을 수 없는 id 입니다.");
        });

        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return new UserRespDto(userEntity);

    }


}
