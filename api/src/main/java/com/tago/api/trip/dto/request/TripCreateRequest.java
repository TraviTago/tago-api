package com.tago.api.trip.dto.request;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.trip.dto.TripCreateDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TripCreateRequest {
    private String name;
    private LocalDateTime dateTime;
    private int currentCnt;
    private int maxCnt;
    private Boolean sameGender;
    private Boolean sameAge;
    private Boolean isPet;
    private String meetPlace;
    private List<String> tags;
    private List<Long> places;

    public TripCreateDto toTripCreateDto() {
        return TripCreateDto.builder()
                .name(name)
                .dateTime(dateTime)
                .currentCnt(currentCnt)
                .maxCnt(maxCnt)
                .sameGender(sameGender)
                .sameAge(sameAge)
                .isPet(isPet)
                .meetPlace(meetPlace)
                .tags(Favorite.from(tags))
                .places(places)
                .build();
    }
}
