package com.tago.api.trip.application;

import com.tago.api.common.mapper.OriginTripDtoMapper;
import com.tago.api.trip.dto.response.OriginTripGetResponse;
import com.tago.api.trip.dto.response.OriginTripGetAllResponse;
import com.tago.domain.trip.domain.OriginTrip;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.handler.OriginTripQueryService;
import com.tago.domain.trip.handler.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginTripGetService {

    private final OriginTripQueryService originTripQueryService;
    private final TripQueryService tripQueryService;

    @Transactional(readOnly = true)
    public OriginTripGetAllResponse getAll() {
        List<OriginTrip> trips = originTripQueryService.findAll();
        return new OriginTripGetAllResponse(OriginTripDtoMapper.toOriginTripInfo(trips));
    }

    @Transactional(readOnly = true)
    public OriginTripGetResponse getByName(String name) {
        OriginTrip originTripInfo = originTripQueryService.findByName(name);
        List<Trip> trips = tripQueryService.findByName(name);

        return new OriginTripGetResponse(
                originTripInfo.getOverview(),
                OriginTripDtoMapper.toOriginTripDetail(trips)
        );
    }
}
