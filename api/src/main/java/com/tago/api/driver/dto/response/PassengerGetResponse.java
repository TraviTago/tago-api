package com.tago.api.driver.dto.response;

import com.tago.domain.driver.dto.PassengerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerGetResponse {
    private List<PassengerDto> members;
}
