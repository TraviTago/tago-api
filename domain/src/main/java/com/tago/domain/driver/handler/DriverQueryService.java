package com.tago.domain.driver.handler;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.exception.DriverNotFoundException;
import com.tago.domain.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DriverQueryService {

    private final DriverRepository driverRepository;

    public Driver findByCode(String code) {
        return driverRepository.findByCode(code)
                .orElseThrow(DriverNotFoundException::new);
    }

    public Driver findById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(DriverNotFoundException::new);
    }
}

