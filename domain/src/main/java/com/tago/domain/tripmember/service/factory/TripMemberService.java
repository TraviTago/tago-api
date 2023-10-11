package com.tago.domain.tripmember.service.factory;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;

public interface TripMemberService {
    String getState();
    void action(Trip trip, Member member);
}
