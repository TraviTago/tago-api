package com.tago.api.trip.application;

import com.tago.api.trip.dto.response.TripGetAllResponse;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.place.domain.Place;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.domain.TripPlace;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.trip.mapper.TripDtoMapper;
import com.tago.domain.trip.repository.TripMemberRepository;
import com.tago.domain.trip.repository.TripRepository;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripQueryService tripQueryService;
    private final TripMemberRepository tripMemberRepository;

    @Transactional(readOnly = true)
    public TripGetAllResponse getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.findAll(cursorId, cursorDate, limit);
        return new TripGetAllResponse(hasNext(trips), trips);
    }

    private boolean hasNext(List<TripPreviewDto> trips) {
        return !trips.isEmpty();
    }

    public TripStatusResponse getTripStatus(Long tripId){

        Trip trip = tripQueryService.findByID(tripId);

        //해당 여행에 참여하는 사람들 조회
        List<TripMember> tripMembers = tripMemberRepository.findByTripId(tripId);

        int femaleCnt = countGender(tripMembers,Gender.FEMALE);
        int maleCnt = countGender(tripMembers,Gender.MALE);
        Set<String> ageGroups = getAgeGroups(tripMembers);


        LocalDateTime endTime = trip.getDateTime().plusHours(8);

        return new TripStatusResponse(
                femaleCnt,
                maleCnt,
                new ArrayList<>((ageGroups)),
//                trip.getDateTime().toString(),
//                endTime.toString()
                formatToHourMinute(trip.getDateTime()),
                formatToHourMinute(endTime)
        );

    }

    //성별 count
    private int countGender(List<TripMember> tripMembers, Gender gender) {
        return (int) tripMembers.stream()
                .map(TripMember::getMember)
                .map(Member::getProfile)
                .filter(profile -> profile.getGender() == gender)
                .count();
    }

    private Set<String > getAgeGroups(List<TripMember> tripMembers){
        return tripMembers.stream()
                .map(TripMember::getMember)
                .map(Member::getProfile)
                .map(profile -> calAgeGroup(profile.getAgeRange()))
                .collect(Collectors.toSet());
    }

    private String calAgeGroup(int ageRange){
        return ageRange+"대";
    }

    private String formatToHourMinute(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }

}
