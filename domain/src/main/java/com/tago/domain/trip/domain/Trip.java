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

    @Setter
    private int max_member;

    @Setter
    private int current_member;

    @Embedded
    private Condition condition;

    @Default
    @Convert(converter = FavoriteEnumArrayConverter.class)
    private List<Favorite> favorites = new ArrayList<>();
}


//@Service
//@RequiredArgsConstructor
//public class TripMemberService {
//
//    private final TripMemberCommandService tripMemberCommandService;
//    private final TripCommandService tripCommandService;
//
//    public TripMemberJoinResponse joinTrip(Long tripId, Long memberId){
//
//        // Check if the tripId is valid
//        if (!tripCommandService.isValidTripId(tripId)){
//            throw new IllegalArgumentException("Invalid tripId");
//        }
//
//        // Get the current members and max members of the trip
//        Trip trip = tripCommandService.getTripById(tripId);
//
//        // Check if the current members exceed or equal to max members
//        if(trip.getCurrent_member() >= trip.getMax_member()){
//            throw new IllegalArgumentException("The trip has reached its maximum members limit");
//        }
//
//        // Increase the current members by 1
//        tripCommandService.incrementCurrentMember(trip);
//
//        // Create a new trip member and return
//        TripMember tripMember = create(tripId, memberId);
//        return new TripMemberJoinResponse(tripMember.getId(),tripMember.getTripId(),tripMember.getMemberId());
//    }
//
//    // ... Other methods remain unchanged ...
//}




























