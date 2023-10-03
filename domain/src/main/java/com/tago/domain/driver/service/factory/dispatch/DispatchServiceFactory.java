package com.tago.domain.driver.service.factory.dispatch;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DispatchServiceFactory {

    private final Map<String, DispatchService> services;

    public DispatchServiceFactory(List<DispatchService> services) {
        this.services = services.stream().collect(
                Collectors.toUnmodifiableMap(DispatchService::getState, Function.identity())
        );
    }

    public DispatchService getInstance(String state) {
        return services.get(state);
    }
}
