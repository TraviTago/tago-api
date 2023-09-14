package com.tago.domain.course.domain;

import com.tago.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

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

    @Column(nullable = false)
    private int order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @BatchSize(size = 10)
    @OneToOne(fetch = FetchType.LAZY)
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
