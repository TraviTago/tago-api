package com.tago.domain.tripmember.repository;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripMemberRepository extends JpaRepository<TripMember,Long>, TripMemberCustomRepository {

    Boolean existsByTripAndMember(Trip trip, Member member);
    void deleteByMemberId(Long memberId);
}
