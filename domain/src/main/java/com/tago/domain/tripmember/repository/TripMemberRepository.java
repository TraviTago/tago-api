package com.tago.domain.tripmember.repository;

import com.tago.domain.tripmember.domain.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TripMemberRepository extends JpaRepository<TripMember,Long>, TripMemberCustomRepository {

    Boolean existsByTripIdAndMemberId(Long tripId, Long memberId);

    Optional<TripMember> findByTripIdAndMemberId(Long tripId, Long memberId);
}
