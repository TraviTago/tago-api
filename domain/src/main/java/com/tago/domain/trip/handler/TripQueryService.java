package com.tago.domain.trip.handler;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.exception.TripNotFoundException;
import com.tago.domain.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripQueryService {

    private final TripRepository tripRepository;

    public Trip findById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(TripNotFoundException::new);
    }
  
    public List<Trip> findAll(Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet) {
        List<Trip> trips = tripRepository.findAllFetchTripPlaceAndPlace(cursorId, cursorDate, limit, sameGender, isPet);

        if (trips.isEmpty()) {
            throw new TripNotFoundException();
        }

        return trips;
    }

    public List<Trip> searchByPlaceTitleKeyword(
            String keyword, Long cursorId, LocalDateTime cursorDate, int limit
    ) {
        List<Trip> trips = tripRepository.findByPlaceTitleKeywordContain(keyword, cursorId, cursorDate, limit);

        if (trips.isEmpty()) {
            throw new TripNotFoundException();
        }

        return trips;
    }

    public Trip findByTripTag(Long memberId) {
        return tripRepository.findByTripTag(memberId);
    }

    public List<Trip> findAllByMember(Member member) {
        return tripRepository.findAllByMember(member);
    }
}
