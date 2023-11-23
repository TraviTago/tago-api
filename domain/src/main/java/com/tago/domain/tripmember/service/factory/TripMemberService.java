package com.tago.domain.tripmember.service.factory;

public interface TripMemberService {
    String getState();
    void action(Long tripId, Long memberId);
}
