package com.tago.domain.trip.domain;

import com.tago.domain.common.converter.FavoriteEnumArrayConverter;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.member.domain.vo.Favorite;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long courseId;

    private String name;

    private LocalDateTime meetTime;

    private String meetPlace;

    private int max_member;

    private int current_member;

    @Embedded
    private Condition condition;

    @Default
    @Convert(converter = FavoriteEnumArrayConverter.class)
    private List<Favorite> favorites = new ArrayList<>();
}
