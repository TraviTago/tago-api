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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long typeId;

    @Column(name = "created_time", nullable = false)
    private String createdTime;

    @Column(name = "modified_time", nullable = false)
    private String modifiedTime;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(name = "mapx", nullable = false)
    private double mapX;

    @Column(name = "mapy", nullable = false)
    private double mapY;

    @Column(name = "visit", nullable = false)
    private double visit;

<<<<<<< HEAD
    @Column(length = 1000)
=======
    @Column(columnDefinition = "TEXT")
>>>>>>> 6b647c0 (feat: inserter분리)
    private String overview;

    @Embedded
    private Info info;
}
