package com.tago.domain.driver.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.driver.domain.Driver;
import com.tago.domain.driver.domain.QDriver;
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
    public Optional<DriverInfoDto> findByDriverAndCar(int cnt, Driver driver) {
        return Optional.ofNullable(queryFactory.select(new QDriverInfoDto(
                        QDriver.driver.info.imgUrl,
                        QDriver.driver.info.name,
                        QDriver.driver.info.comment,
                        QDriver.driver.info.phoneNumber,
                        QDriver.driver.info.license,
                        car.seater,
                        car.number
                 ))
                .from(QDriver.driver)
                .innerJoin(QDriver.driver.cars, car)
                .where(
                        driverEq(driver),
                        carSeaterEq(cnt)
                ).fetchOne());
    }

    private BooleanExpression driverEq(Driver driver) {
        return QDriver.driver.eq(driver);
    }

    private BooleanExpression carSeaterEq(int cnt) {
        return cnt > 4 ? car.seater.eq(8) : car.seater.eq(4);
    }
}
