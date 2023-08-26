package com.tago.domain.tripmember.repository;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.MyTripDto;

import java.util.List;

public interface TripMemberCustomRepository {
    List<Trip> findTripsByMember(Member member);
}
