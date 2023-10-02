package com.tago.domain.taxi.repository;

import com.tago.domain.taxi.domain.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi,Long> {
}
