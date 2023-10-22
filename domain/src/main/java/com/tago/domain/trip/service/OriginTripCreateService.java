package com.tago.domain.trip.service;

import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.dto.OriginTripPlaceCreateDto;
import com.tago.domain.trip.repository.OriginTripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OriginTripCreateService {
    private static final String originTripName1 = "우리 같이 바다갈래?";
    private static final String originTripName2 = "가을에 부산에 가야하는 이유";
    private static final String originTripName3 = "노을맛집, 다대포에서 커피한잔";
    private static final Long adminId = 1L;
    private static final int totalCnt = 3;

    private final OriginTripRepository  originTripRepository;

    @Transactional
    public void create() {
        List<OriginTripCreateDto> originTrips = List.of(
                createOriginTrip(originTripName1, List.of(2672393L, 2770124L, 2758477L, 1479L, 2350092L)),
                createOriginTrip(originTripName2, List.of(126148L, 130L, 2385686L, 2891814L, 126081L)),
                createOriginTrip(originTripName3, List.of(2870289L, 2678611L, 1805965L, 132191L, 126079L))
        );

        originTripRepository.saveAllTrip(originTrips);
        List<OriginTripPlaceCreateDto> places = createOriginTripPlaces(originTrips);
        originTripRepository.saveAllTripPlace(places);
    }

    private OriginTripCreateDto createOriginTrip(String name, List<Long> placeIds) {
        return OriginTripCreateDto.builder()
                .name(name)
                .dateTime(getDateTime())
                .meetPlace("해운대역 4번출구")
                .totalTime(480)
                .maxCnt(4)
                .memberId(adminId)
                .places(placeIds)
                .build();
    }

    private List<OriginTripPlaceCreateDto> createOriginTripPlaces(List<OriginTripCreateDto> originTrips) {
        Long lastInsertTripId = originTripRepository.lastInsertId();
        System.out.println("################# last_insert_id: " + lastInsertTripId);

        IntStream.range(0, totalCnt)
                .forEach(idx -> {
                    Long tripId = lastInsertTripId + (long) idx;
                    originTrips.get(idx).setId(tripId);  // PK 설정
                });

        return originTrips.stream()
                .map(trip -> createOriginTripPlace(trip.getId(), trip.getPlaces()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<OriginTripPlaceCreateDto> createOriginTripPlace(Long tripId, List<Long> placeIds) {
        return IntStream.range(0, placeIds.size())
                .mapToObj(idx -> createOriginTripPlaceDto(idx, tripId, placeIds.get(idx)))
                .toList();
    }

    private OriginTripPlaceCreateDto createOriginTripPlaceDto(int order, Long tripId, Long placeId) {
        return OriginTripPlaceCreateDto.builder()
                .order(order)
                .tripId(tripId)
                .placeId(placeId)
                .build();
    }

    private LocalDateTime getDateTime() {
        return LocalDateTime.of(
                LocalDate.now().plusWeeks(2),
                LocalTime.of(12, 0)
        );
    }
}
