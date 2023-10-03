package com.tago.api.driver.application;

import com.tago.api.common.security.jwt.JwtTokenPublisher;
import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import com.tago.api.driver.dto.request.LoginRequest;
import com.tago.api.driver.dto.response.LoginResponse;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.handler.DriverQueryService;
import com.tago.domain.member.domain.vo.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverAuthService {

    private final DriverQueryService driverQueryService;
    private final JwtTokenPublisher jwtTokenPublisher;

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        Driver driver = driverQueryService.findByCode(request.getCode());
        return new LoginResponse(jwtTokenPublisher.generateTokens(driver.getId(), Role.DRIVER));
    }

    @Transactional
    public JwtTokenDto reissue(Long driverId) {
        Driver driver = driverQueryService.findById(driverId);
        return jwtTokenPublisher.generateAccessToken(driver.getId(), Role.DRIVER);
    }
}
