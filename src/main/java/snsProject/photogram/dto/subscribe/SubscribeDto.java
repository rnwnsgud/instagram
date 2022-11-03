package snsProject.photogram.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
    private int id; // 구독버튼 클릭할 때 누구를 구독할지 : toUserId
    private String username; // 구독정보에 나올 사용자 이름
    private String profileImageUrl; // 구독정보에 나올 사용자 사진
    private Integer subscribeState; // 구독한 상태인가?
    private Integer equalUserState; // 구독한 사람과 로그인사용자가 같은가?
}
