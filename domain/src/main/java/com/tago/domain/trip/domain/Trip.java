package com.tago.domain.trip.domain;

import com.tago.domain.common.converter.FavoriteEnumArrayConverter;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.exception.MaxMemberLimitException;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.member.domain.vo.Favorite;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import org.hibernate.annotations.BatchSize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
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

    @Column(name = "total_time")
    private int totalTime;

    @Column(name = "max_cnt")
    private int maxCnt;

    @Column(name = "current_cnt")
    private int currentCnt;

    @Embedded
    private Condition condition;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripPlace> tripPlaces = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripTag> tripTags = new ArrayList<>();

    public void join() {
        if (isLimitMember()) throw new MaxMemberLimitException();
        this.currentCnt += 1;
    }

    private boolean isLimitMember() {
        return this.currentCnt >= this.maxCnt;
    }

    public LocalDateTime getEndTime() {
        return this.dateTime.plusMinutes(totalTime);
    }

    public void addTripPlaces(List<TripPlace> places) {
        tripPlaces.addAll(places);
    }

    public void addTripTags(List<TripTag> tags) {
        tripTags.addAll(tags);
    }
}