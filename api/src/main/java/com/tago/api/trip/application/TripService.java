package com.tago.api.trip.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.domain.TripMember;
import com.tago.domain.trip.dto.TripPreviewDto;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import com.tago.domain.trip.service.TripQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripQueryService tripQueryService;
    private final TripMemberRepository tripMemberRepository;

    @Transactional(readOnly = true)
    public PageResponseDto<TripPreviewDto> getAll(Long cursorId, LocalDateTime cursorDate, int limit) {
        List<TripPreviewDto> trips = tripQueryService.findAll(cursorId, cursorDate, limit);
        return PageResponseDto.from(trips);
    }

    public TripStatusResponse getTripStatus(Long tripId){

        Trip trip = tripQueryService.findById(tripId);

        //해당 여행에 참여하는 사람들 조회
        List<TripMember> tripMembers = tripMemberRepository.findByTripId(tripId);

        int femaleCnt = countGender(tripMembers,Gender.FEMALE);
        int maleCnt = countGender(tripMembers,Gender.MALE);
        Set<String> ageGroups = getAgeGroups(tripMembers);


        LocalDateTime endTime = trip.getDateTime().plusHours(8);

        return new TripStatusResponse(
                femaleCnt,
                maleCnt,
                ageGroups,
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

    private Set<String> getAgeGroups(List<TripMember> tripMembers){
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
