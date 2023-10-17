package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.OriginTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TagoTripRepository extends JpaRepository<OriginTrip, Long>{

    Optional<OriginTrip> findByName(@Param("name")String name);
}
