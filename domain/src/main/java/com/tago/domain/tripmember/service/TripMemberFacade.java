package com.tago.domain.tripmember.service;

import com.tago.domain.tripmember.service.factory.TripMemberService;
import com.tago.domain.tripmember.service.factory.TripMemberServiceFactory;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TripMemberFacade {

    private final TripMemberServiceFactory tripMemberServiceFactory;
    private final RedissonClient redissonClient;

    @Transactional
    public void createOrDelete(Long tripId, Long memberId, String state) {
        TripMemberService tripMemberService = tripMemberServiceFactory.getInstance(state);

        String key = "LOCK-" + state + "-" + tripId;
        RLock lock = redissonClient.getLock(key);

        try {
            boolean availableLock = lock.tryLock(5, 3, TimeUnit.SECONDS);

            if (!availableLock) {
                System.out.println("LOCK 획득 실패");
                return;
            }

            tripMemberService.action(tripId, memberId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
