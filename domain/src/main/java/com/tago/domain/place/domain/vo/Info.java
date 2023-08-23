package com.tago.domain.place.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Builder
@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Info {
    private String homepage;

    private String telephone;

    private String restDate;

    private String openTime;

    private String parking;
}
