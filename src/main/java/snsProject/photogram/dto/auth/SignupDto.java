package snsProject.photogram.dto.auth;

import lombok.Data;
import snsProject.photogram.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

    @Size(max = 20) // @max : 숫자 사용하는 필드의 길이 제한
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .build();
    }
}
