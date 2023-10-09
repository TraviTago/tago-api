package com.tago.domain.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TagoTripOneDto {
    private Long id;
    private LocalDateTime dateTime;
    private int maxMember;
    private int currentMember;
}
