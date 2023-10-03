package com.tago.domain.driver.repository;

import com.tago.domain.driver.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {
}
