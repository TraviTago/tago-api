package com.tago.domain.driver.event.producer;

import com.tago.domain.common.event.Event;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DispatchEvent implements Event {
    private Long tripId;
    private String phoneNumber;
    private String name;
    private Action action;

    @Getter
    public enum Action {
        CREATE, DELETE
    }
}
