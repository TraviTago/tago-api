package com.tago.domain.driver.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.driver.dto.DriverInfoDto;
import com.tago.domain.driver.dto.QDriverInfoDto;
import com.tago.domain.driver.repository.DriverCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.tago.domain.driver.domain.QCar.car;
import static com.tago.domain.driver.domain.QDriver.driver;

@Repository
@RequiredArgsConstructor
public class DriverCustomRepositoryImpl implements DriverCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<DriverInfoDto> findById(int cnt, Long driverId) {
        return Optional.ofNullable(queryFactory.select(new QDriverInfoDto(
                        driver.info.imgUrl,
                        driver.info.name,
                        driver.info.comment,
                        driver.info.phoneNumber,
                        driver.info.license,
                        car.seater,
                        car.number
                 ))
                .from(driver)
                .innerJoin(driver.cars, car)
                .where(
                        driverIdEq(driverId),
                        carSeaterEq(cnt)
                ).fetchOne());
    }

    private BooleanExpression driverIdEq(Long driverId) {
        return driver.id.eq(driverId);
    }

    private BooleanExpression carSeaterEq(int cnt) {
        return cnt > 4 ? car.seater.eq(8) : car.seater.eq(4);
    }
}
