package com.tago.api.trip.application;

import com.tago.domain.trip.dto.TripCreateDto;
import com.tago.domain.trip.service.TripCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripManageService {

    private final TripCreateService tripCreateService;

    public void create(Long memberId, List<TripCreateDto> trips) {

    }
}
