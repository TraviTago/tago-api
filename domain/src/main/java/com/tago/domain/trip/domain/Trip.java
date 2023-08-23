package com.tago.domain.trip.domain;

import com.tago.domain.common.converter.FavoriteEnumArrayConverter;
import com.tago.domain.member.domain.Member;
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

    private String name;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "meet_place")
    private String meetPlace;

    @Column(name = "max_cnt")
    private int maxCnt;

    @Column(name = "current_cnt")
    private int currentCnt;

    @Embedded
    private Condition condition;

    @Default
    @Convert(converter = FavoriteEnumArrayConverter.class)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Default
    @OneToMany(mappedBy = "trip")
    private List<TripPlace> tripPlaces = new ArrayList<>();

    public void incrementCurrentMember() {
        this.current_member += 1;
    }
}






























