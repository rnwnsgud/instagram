package snsProject.photogram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import snsProject.photogram.handler.exception.CustomApiException;
import snsProject.photogram.repository.SubscribeRepository;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

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
