package com.tago.domain.tripmember.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.MyTripDto;
import com.tago.domain.tripmember.mapper.MyTripDtoMapper;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripMemberQueryService {

    private final TripMemberRepository tripMemberRepository;

    public List<MyTripDto> findTripsByMember(Member member) {
        List<Trip> trips = tripMemberRepository.findTripsByMember(member);
        return MyTripDtoMapper.toMyTripDto(trips);
    }
}
