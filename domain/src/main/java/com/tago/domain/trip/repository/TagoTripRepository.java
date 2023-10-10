package com.tago.domain.trip.repository;

import com.tago.domain.trip.domain.TagoTrip;
import com.tago.domain.trip.domain.TripPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TagoTripRepository extends JpaRepository<TagoTrip, Long>{
    @Query(value = "SELECT source FROM tago_trip WHERE name = :name", nativeQuery = true)
    String findSourceByName(@Param("name") String name);


}
