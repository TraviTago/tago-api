package com.tago.domain.trip.handler;

import com.tago.domain.driver.domain.Driver;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import com.tago.domain.trip.repository.OriginTripRepository;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripQueryService {

    private final TripRepository tripRepository;
    private final OriginTripRepository originTripRepository;

    public Trip findById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
    }

    public Trip findByIdFetchTripMember(Long tripId) {
        return tripRepository.findByIdFetchTripMember(tripId)
                .orElseThrow(TripNotFoundException::new);
    }
  
    public List<Trip> findAll(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet, Gender memberGender) {
        return tripRepository.findAll(cursorId, cursorDate, limit, sameGender, isPet, memberGender);
    }

    public List<Trip> findAllByNotDispatch(Long cursorId, LocalDateTime cursorDate, int limit) {
        return tripRepository.findAllByNotDispatch(cursorId, cursorDate, limit);
    }

    public List<Trip> searchByPlaceTitleKeyword(
            String keyword, Long cursorId, LocalDateTime cursorDate, int limit
    ) {
        return tripRepository.findByPlaceTitleKeywordContain(keyword, cursorId, cursorDate, limit);
    }

    public Trip findByTripTag(Long memberId) {
        return tripRepository.findByTripTag(memberId);
    }

    public List<Trip> findAllByMember(Member member) {
        return tripRepository.findAllByMember(member);
    }

    public List<Trip> findAllByDriver(Driver driver) {
        return tripRepository.findAllByDriver(driver);
    }

    public List<Trip> findByNameAndDateTimeAfter(String name) {
        return tripRepository.findByNameAndDateTimeAfter(name, LocalDateTime.now());
    }
}
