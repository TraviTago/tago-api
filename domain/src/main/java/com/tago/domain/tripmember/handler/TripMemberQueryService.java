package com.tago.domain.tripmember.handler;

import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import com.tago.domain.tripmember.exception.TripMemberNotFoundException;
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

    public Boolean existsByTripIdAndMemberId(Long tripId, Long memberId) {
        return tripMemberRepository.existsByTripIdAndMemberId(tripId, memberId);
    }

    public TripMember findByTripIdAndMemberId(Long tripId, Long memberId) {
        TripMember tripMember = tripMemberRepository.findByTripIdAndMemberId(tripId, memberId);
        if (tripMember == null) {
            throw new TripMemberNotFoundException();
        }
        return tripMember;
    }

}
