package com.tago.api.tripmember.application;


import com.tago.domain.trip.domain.TripMember;
import com.tago.domain.trip.service.TripMemberCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripMemberService {

    @Autowired
    private TripMemberCommandService tripMemberCommandService;


    public TripMember joinTrip(Long trip_id, Long member_id) {
        return create(trip_id, member_id);
    }

    private TripMember create(Long trip_id, Long member_id) {
        TripMember tripMember = TripMember.builder()
                .trip_id(trip_id)
                .member_id(member_id)
                .build();

        return tripMemberCommandService.save(tripMember);
    }
}
