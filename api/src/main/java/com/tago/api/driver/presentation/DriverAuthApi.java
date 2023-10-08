package com.tago.api.driver.presentation;

import com.tago.api.common.dto.ResponseDto;
import com.tago.api.common.security.annotation.DriverAuthentication;
import com.tago.api.common.security.jwt.dto.JwtTokenDto;
import com.tago.api.driver.application.DriverAuthService;
import com.tago.api.driver.dto.request.LoginRequest;
import com.tago.api.driver.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/taxi/auth")
public class DriverAuthApi {

    private final DriverAuthService driverAuthService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = driverAuthService.login(request);
        return ResponseDto.ok(response);
    }

    @PostMapping("/token/reissue")
    public ResponseEntity<JwtTokenDto> reissue(@DriverAuthentication Long driverId) {
        JwtTokenDto response = driverAuthService.reissue(driverId);
        return ResponseDto.ok(response);
    }
}
