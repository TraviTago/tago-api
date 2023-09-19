package com.tago.api.trip.application;

import com.tago.api.trip.dto.request.TripSaveRequest;
import com.tago.api.trip.dto.response.TripSaveResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.service.TripCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TripSaveService {

    private final MemberQueryService memberQueryService;
    private final TripCreateService tripCreateService;

    @Transactional
    public TripSaveResponse create(Long memberId, TripSaveRequest request) {
        Member member = memberQueryService.findById(memberId);
        Trip trip = tripCreateService.create(member, request.toTripCreateDto());
        return new TripSaveResponse(trip.getId());
    }
}
