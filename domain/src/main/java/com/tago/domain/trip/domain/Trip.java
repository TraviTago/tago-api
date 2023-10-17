package com.tago.domain.trip.domain;

import com.tago.domain.common.model.entity.BaseTimeEntity;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.exception.MaxMemberLimitException;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.tripmember.domain.TripMember;
import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip extends BaseTimeEntity {

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

    @Column(name = "origin")
    private boolean origin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripPlace> tripPlaces = new ArrayList<>();

    @Default
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<TripTag> tripTags = new ArrayList<>();

    @Default
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripMember> tripMembers = new ArrayList<>();


    public void join(Member member) {
        if (isLimitMember()) throw new MaxMemberLimitException();
        this.currentCnt += 1;
        addTripMember(member);
    }

    public void leave(Member member) {
        this.currentCnt -= 1;
        deleteTripMember(member);
    }

    private void addTripMember(Member member) {
        this.tripMembers.add(TripMember.builder()
                .trip(this)
                .member(member)
                .build()
        );
    }
    private void deleteTripMember(Member member) {
        List<TripMember> tripMembers = this.tripMembers.stream()
                .filter(tripMember -> !tripMember.getMember().getId().equals(member.getId()))
                .toList();

        this.tripMembers.clear();
        this.tripMembers.addAll(tripMembers);
    }

    public boolean isLimitMember() {
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

    public Boolean isJoined(Member member) {
        return tripMembers.stream()
                .anyMatch(tripMember -> tripMember.isTripMember(member));
    }
}