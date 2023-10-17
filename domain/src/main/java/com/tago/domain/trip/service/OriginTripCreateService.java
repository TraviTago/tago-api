package com.tago.domain.trip.service;

import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.repository.OriginTripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OriginTripCreateService {
    private static final String originTripName1 = "우리 같이 바다갈래?";
    private static final String originTripName2 = "가을에 부산에 가야하는 이유";
    private static final String originTripName3 = "노을맛집, 다대포에서 커피한잔";
    private static final Long adminId = 1L;

    private final OriginTripRepository  originTripRepository;

    public void create() {
        List<OriginTripCreateDto> originTrips = List.of(
                createOriginTrip(originTripName1),
                createOriginTrip(originTripName2),
                createOriginTrip(originTripName3)
        );
        originTripRepository.saveAll(originTrips);
    }

    private OriginTripCreateDto createOriginTrip(String name) {
        return OriginTripCreateDto.builder()
                .name(name)
                .dateTime(getDateTime())
                .meetPlace("해운대역 4번출구")
                .totalTime(480)
                .maxCnt(4)
                .memberId(adminId)
                .build();
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0));
    }
}
