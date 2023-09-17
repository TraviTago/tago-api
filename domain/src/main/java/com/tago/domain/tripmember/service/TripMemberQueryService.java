package com.tago.domain.tripmember.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.MyTripDto;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.mapper.MyTripDtoMapper;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripMemberQueryService {

    private final TripMemberRepository tripMemberRepository;

    public List<MyTripDto> findTripsByMember(Member member) {
        List<Trip> trips = tripMemberRepository.findTripsByMember(member);
        return MyTripDtoMapper.toMyTripDto(trips);
    }

    public List<TripMemberDto> findMembersByTrip(Trip trip) {
        return tripMemberRepository.findMembersByTrip(trip);
    }

    public Boolean existsByTripIdAndMemberId(Long tripId, Long memberId) {
        return tripMemberRepository.existsByTripIdAndMemberId(tripId, memberId);
    }

    public Optional<TripMember> findByTripIdAndMemberId(Long tripId, Long memberId) {
        return tripMemberRepository.findByTripIdAndMemberId(tripId, memberId);
    }

}
