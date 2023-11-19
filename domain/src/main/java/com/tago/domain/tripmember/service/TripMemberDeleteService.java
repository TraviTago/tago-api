package com.tago.domain.tripmember.service;


import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import com.tago.domain.tripmember.event.producer.TripMemberEventProducer;
import com.tago.domain.tripmember.exception.AlreadyExistsTripMemberException;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import com.tago.domain.tripmember.service.factory.TripMemberService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TripMemberDeleteService implements TripMemberService {

    private static final String state = "CANCEL";
    private final TripMemberEventProducer tripMemberEventProducer;
    private final RedissonClient redissonClient;

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void action(Trip trip, Member member) {
        String key = "LOCK-" + state + trip.getId();
        RLock lock = redissonClient.getLock(key);

        try {
            boolean availableLock = lock.tryLock(3, 5, TimeUnit.SECONDS);

            if (!availableLock) {
                System.out.println("LOCK 획득 실패");
                return;
            }
            delete(trip, member);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void delete(Trip trip, Member member) {
        trip.leave(member);
        trip.getTripMembers().forEach(tripMember -> publish(trip, tripMember.getMember()));
    }

    private void publish(Trip trip, Member member) {
        tripMemberEventProducer.produceEvent(TripMemberEvent.builder()
                .tripId(trip.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .action(TripMemberEvent.Action.DELETE)
                .build()
        );
    }
}



