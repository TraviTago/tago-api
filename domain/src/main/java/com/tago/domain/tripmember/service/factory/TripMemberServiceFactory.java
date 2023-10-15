package com.tago.domain.tripmember.service.factory;

import com.tago.domain.driver.service.factory.dispatch.DispatchService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TripMemberServiceFactory {

    private final Map<String, TripMemberService> services;

    public TripMemberServiceFactory(List<TripMemberService> services) {
        this.services = services.stream().collect(
                Collectors.toUnmodifiableMap(TripMemberService::getState, Function.identity())
        );
    }

    public TripMemberService getInstance(String state) {
        return services.get(state);
    }
}
