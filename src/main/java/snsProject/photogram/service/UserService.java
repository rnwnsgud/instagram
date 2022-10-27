package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.domain.User;
import snsProject.photogram.dto.user.UserRespDto;
import snsProject.photogram.handler.exception.CustomValidationApiException;
import snsProject.photogram.repository.UserRepository;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
