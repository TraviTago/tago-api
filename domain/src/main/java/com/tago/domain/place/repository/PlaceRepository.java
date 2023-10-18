package com.tago.domain.place.repository;

import com.tago.domain.place.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceCustomRepository {

    Optional<Place> findByTitle(String title);
}
