package com.tago.domain.driver.repository;

import com.tago.domain.driver.domain.Dispatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispatchRepository extends JpaRepository<Dispatch,Long> {
}
