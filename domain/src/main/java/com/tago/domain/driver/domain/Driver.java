package com.tago.domain.driver.domain;

import com.tago.domain.driver.domain.vo.DriverInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Embedded
    private DriverInfo info;

    @OneToMany(mappedBy = "driver")
    private List<Car> cars = new ArrayList<>();
}
