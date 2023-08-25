package com.tago.domain.tripmember.repository;

import com.tago.domain.tripmember.domain.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripMemberRepository extends JpaRepository<TripMember,Long>, TripMemberCustomRepository {

    List<TripMember> findByTripId(Long tripId);
}
