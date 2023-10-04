package com.tago.domain.driver.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class DriverInfo {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private String license;

    private String imgUrl;
}
