package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.dto.subscribe.SubscribeDto;
import snsProject.photogram.handler.exception.CustomApiException;
import snsProject.photogram.repository.SubscribeRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public List<SubscribeDto> getSubscribeList(int principalId, int pageUserId) {

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("if ((SELECT 1 from subscribe WHERE fromUserId = ? AND toUserId = u.id), 1, 0) as subscribeState, ");
        sb.append("if((? = u.id), 1, 0) as equalUserState ");
        sb.append("FROM user u INNER JOIN subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ? ");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);
        return subscribeDtos;
    }

    public void doSubscribe(int fromUserId, int toUserId) {
        try {
            subscribeRepository.subscribe(fromUserId, toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독했거나 없는 사용자입니다.");
        }

    }

    public void unSubscribe(int fromUserId, int toUserId) {
        subscribeRepository.unSubscribe(fromUserId, toUserId);
    }

}
