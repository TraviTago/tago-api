package com.tago.domain.tripmember.event.producer;

import com.tago.domain.common.event.Event;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class TripMemberEvent implements Event {
    private Long tripId;
    private String name;
    private String phoneNumber;
    private Action action;

    @Getter
    public enum Action {
        CREATE, DELETE, COMPLETE
    }
}
