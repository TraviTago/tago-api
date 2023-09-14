package com.tago.domain.course.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Default
    @OneToMany(mappedBy = "course")
    private List<CoursePlace> coursePlaces = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "course")
    private List<CourseTag> courseTags = new ArrayList<>();
}
