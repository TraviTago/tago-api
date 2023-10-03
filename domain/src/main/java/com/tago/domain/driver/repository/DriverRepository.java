package com.tago.domain.driver.repository;

import com.tago.domain.driver.domain.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByCode(String code);
}
