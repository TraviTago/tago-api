package com.tago.domain.trip.service;

import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberCommandService {
    private final TripMemberRepository tripMemberRepository;
    public TripMember save(TripMember tripMember) {return tripMemberRepository.save(tripMember); }
}
