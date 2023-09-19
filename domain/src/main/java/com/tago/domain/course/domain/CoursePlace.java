package com.tago.domain.course.domain;

import com.tago.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "course_place")
public class CoursePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"order\"")
    private int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public Long getPlaceId() {
        return this.place.getId();
    }

    public String getPlaceTitle() {
        return this.place.getTitle();
    }

    public String getPlaceImgUrl() {
        return this.place.getImgUrl();
    }
}
