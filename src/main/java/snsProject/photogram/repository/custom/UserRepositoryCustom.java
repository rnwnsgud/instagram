package snsProject.photogram.repository.custom;


import snsProject.photogram.dto.user.UserListDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserListDto> findUserWithSearch(String username, String gender);
}
