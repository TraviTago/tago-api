package com.tago.domain.tripmember.service;


import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.event.producer.TripMemberEvent;
import com.tago.domain.tripmember.event.producer.TripMemberEventProducer;
import com.tago.domain.tripmember.exception.AlreadyExistsTripMemberException;
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
        trip.join(member);
        publishEvent(trip, member);
    }

    private void validateJoinedAble(Trip trip, Member member) {
        if (trip.isJoined(member)) {
            throw new AlreadyExistsTripMemberException();
        }
    }

    private void publishEvent(Trip trip, Member member) {
        trip.getTripMembers().stream()
                .filter(tripMember -> !tripMember.getMember().equals(member))
                .forEach(tripMember -> publish(trip, tripMember.getMember(), getEventAction(trip)));
    }

    private void publish(Trip trip, Member member, TripMemberEvent.Action action) {
        tripMemberEventProducer.produceEvent(TripMemberEvent.builder()
                .tripId(trip.getId())
                .name(member.getName())
                .phoneNumber(member.getPhoneNumber())
                .action(action)
                .build()
        );
    }

    private TripMemberEvent.Action getEventAction(Trip trip) {
        if (trip.isLimitMember()) {
            return TripMemberEvent.Action.COMPLETE;
        }
        return TripMemberEvent.Action.CREATE;
    }
}



