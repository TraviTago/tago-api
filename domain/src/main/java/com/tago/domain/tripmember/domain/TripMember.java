package com.tago.domain.tripmember.domain;

import com.tago.domain.member.domain.Member;
import com.tago.domain.trip.domain.Trip;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TripMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Boolean isTripMember(Member member) {
        return this.member.equals(member);
    }
}


