package snsProject.photogram.dto.user;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import snsProject.photogram.domain.User;

@Data
public class UserRespDto {

    private String username;
    private String name;
    private String bio;
    private String website;
    private String phone;
    private String gender;

    @JsonIgnore
    private User user;


    public UserRespDto(User user) {
        this.user = user;
        this.username = user.getUsername();
        this.name = user.getName();
        this.bio = user.getBio();
        this.website = user.getWebsite();
        this.phone = user.getPhone();
        this.gender = user.getGender();
    }
}
