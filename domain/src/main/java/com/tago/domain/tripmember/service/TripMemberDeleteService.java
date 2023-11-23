package com.tago.domain.tripmember.service;


import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.TripQueryService;
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
    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void action(Long tripId, Long memberId) {
        Trip trip = tripQueryService.findById(tripId);
        Member member = memberQueryService.findById(memberId);

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



