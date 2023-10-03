package com.tago.domain.driver.handler;

import com.tago.domain.driver.domain.Dispatch;
import com.tago.domain.driver.repository.DispatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchCommandService {

    private final DispatchRepository dispatchRepository;

    public Dispatch save(Dispatch dispatch) {
        return dispatchRepository.save(dispatch);
    }

    public void delete(Dispatch dispatch) {
        dispatchRepository.delete(dispatch);
    }
}
