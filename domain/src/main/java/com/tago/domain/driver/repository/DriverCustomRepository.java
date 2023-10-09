package com.tago.domain.driver.repository;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.dto.DriverInfoDto;

import java.util.Optional;

public interface DriverCustomRepository {
    Optional<DriverInfoDto> findByDriverAndCar(int cnt, Driver driver);
}
