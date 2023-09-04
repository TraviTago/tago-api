package com.tago.api.common.mapper;

import com.tago.api.common.util.TimeUtil;
import com.tago.api.trip.dto.response.TripStatusResponse;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.tripmember.dto.TripMemberDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TripStatusDtoMapper {

    public static TripStatusResponse toDto(Trip trip, List<TripMemberDto> tripMembers) {
        return new TripStatusResponse(
                countGender(tripMembers, Gender.FEMALE),
                countGender(tripMembers, Gender.MALE),
                getAgeGroups(tripMembers),
                TimeUtil.formatToHourMinute(trip.getDateTime()),
                TimeUtil.formatToHourMinute(trip.getEndTime())
        );
    }

    private static int countGender(List<TripMemberDto> tripMembers, Gender gender) {
        return (int) tripMembers.stream()
                .filter(tripMember -> isGenderEquals(tripMember, gender))
                .count();
    }

    private static boolean isGenderEquals(TripMemberDto tripMember, Gender gender) {
        return gender.isEquals(tripMember.getGender());
    }

    private static Set<Integer> getAgeGroups(List<TripMemberDto> tripMembers){
        return tripMembers.stream()
                .map(TripMemberDto::getAgeRange)
                .collect(Collectors.toSet());
    }
}
