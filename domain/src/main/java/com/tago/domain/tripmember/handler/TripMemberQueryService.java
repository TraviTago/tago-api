package com.tago.domain.tripmember.handler;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemberQueryService {

    private final TripMemberRepository tripMemberRepository;

    public List<TripMemberDto> findMembersByTrip(Trip trip) {
        return tripMemberRepository.findMembersByTrip(trip);
    }

    public Boolean existsByTripAndMember(Trip trip, Member member) {
        return tripMemberRepository.existsByTripAndMember(trip, member);
    }
}
