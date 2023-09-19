package com.tago.domain.trip.domain;

import com.tago.domain.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "trip_tag")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TripTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public TripTag(Trip trip, Tag tag) {
        this.trip = trip;
        this.tag = tag;
    }
}
