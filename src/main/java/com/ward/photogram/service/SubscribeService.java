package com.ward.photogram.service;

import com.ward.photogram.domain.subscribe.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기(Long fromUserId, Long toUserId){
        subscribeRepository.mSubscribe(fromUserId, toUserId);
    }

    @Transactional
    public void 구독취소하기(Long fromUserId, Long toUserId){
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }
}
