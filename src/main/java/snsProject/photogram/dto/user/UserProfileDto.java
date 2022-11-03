package snsProject.photogram.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import snsProject.photogram.domain.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
    private boolean pageOwnerState;
    private User user;
    private int imageCount;

    private boolean subscribeState;
    private int subscribeCount;

}
