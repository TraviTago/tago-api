package com.tago.domain.trip.domain;

import com.tago.domain.common.model.entity.BaseTimeEntity;
import com.tago.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "trip_place")
public class TripPlace extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"order\"")
    private int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public String getPlaceTitle() {
        return this.getPlace().getTitle();
    }
}
