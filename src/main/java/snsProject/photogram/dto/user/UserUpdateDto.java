package snsProject.photogram.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import snsProject.photogram.domain.User;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Data
public class UserUpdateDto {

    @NotBlank
    private String name; //필수
    @NotBlank
    private String password; //필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    //컨트롤러에는 전달하지 않는다
    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
