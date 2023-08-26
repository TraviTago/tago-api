package com.tago.domain.tripmember.service;

import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TripMemberCommandService {
    private final TripMemberRepository tripMemberRepository;
    public TripMember save(TripMember tripMember) {
        return tripMemberRepository.save(tripMember);
    }
}


