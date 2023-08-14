package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.TripMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripMemberRepository extends JpaRepository<TripMember,Long> {

}
