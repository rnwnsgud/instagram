package snsProject.photogram.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserListDto {

    private int id;
    private String username;
    private String name;
    private String bio;
    private String email;
    private String gender;
}
