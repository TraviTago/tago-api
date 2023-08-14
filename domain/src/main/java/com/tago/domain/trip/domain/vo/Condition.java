package com.tago.domain.trip.domain.vo;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Condition {

    @Column(name = "same_gender", nullable = false)
    private boolean sameGender;

    @Column(name = "same_age", nullable = false)
    private boolean sameAge;

    @Column(name = "is_pet", nullable = false)
    private boolean isPet;
}
