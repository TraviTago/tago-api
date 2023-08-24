package com.tago.domain.place.repository;

import com.tago.domain.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceCustomRepository {
}
