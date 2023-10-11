package com.tago.domain.tripmember.service;


import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import com.tago.domain.tripmember.event.producer.TripMemberEventProducer;
import com.tago.domain.tripmember.exception.AlreadyExistsTripMemberException;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import com.tago.domain.tripmember.service.factory.TripMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TripMemberCreateService implements TripMemberService {

    private static final String state = "ACCEPT";
    private final TripMemberEventProducer tripMemberEventProducer;

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void action(Trip trip, Member member) {
        validateJoinedAble(trip, member);
        trip.getTripMembers().forEach(this::publish);
        trip.join(member);
    }

    private void validateJoinedAble(Trip trip, Member member) {
        if (trip.isJoined(member)) {
            throw new AlreadyExistsTripMemberException();
        }
    }

    private void publish(TripMember tripMember) {
        tripMemberEventProducer.produceEvent(TripMemberEvent.builder()
                .name(tripMember.getMember().getName())
                .phoneNumber(tripMember.getMember().getPhoneNumber())
                .action(TripMemberEvent.Action.CREATE)
                .build()
        );
    }
}



