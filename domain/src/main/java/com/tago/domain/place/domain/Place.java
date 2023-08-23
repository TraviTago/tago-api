package com.tago.domain.place.domain;


import com.tago.domain.place.domain.vo.Info;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long typeId;

    @Column(name = "created_time", nullable = false)
    private LocalDateTime createdTime;

    @Column(name = "modified_time", nullable = false)
    private LocalDateTime modifiedTime;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "mapx", nullable = false)
    private double mapX;

    @Column(name = "mapy", nullable = false)
    private double mapY;

    @Column(columnDefinition = "TEXT")
    private String overview;

    @Embedded
    private Info info;
}
