package com.tago.domain.driver.repository;

import com.tago.domain.driver.dto.DriverInfoDto;

import java.util.Optional;

public interface DriverCustomRepository {
    Optional<DriverInfoDto> findById(int cnt, Long driverId);
}
