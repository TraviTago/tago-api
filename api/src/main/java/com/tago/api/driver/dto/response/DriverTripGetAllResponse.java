package com.tago.api.driver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DriverTripGetAllResponse {
    List<TripGetResponse> trips;
}
