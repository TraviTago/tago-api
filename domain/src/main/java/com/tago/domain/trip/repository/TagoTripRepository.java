package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.TagoTrip;
import com.tago.domain.trip.domain.TripPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagoTripRepository extends JpaRepository<TagoTrip, Long>{
}
