package com.tago.domain.trip.domain;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TripMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trip_id;

    private Long member_id;

}

