package com.tago.domain.member.domain;


import com.tago.domain.member.domain.vo.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniqueAccount", columnNames = {"email", "oauth_provider"})
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "oauth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuthProvider oauthProvider;

    @Embedded
    private Profile profile;

    public void updateInfo(int ageRange, Gender gender, Mbti mbti,
                           List<Favorite> favorites, List<TripType> tripTypes
    ) {
        this.profile = Profile.builder()
                .ageRange(ageRange)
                .gender(gender)
                .mbti(mbti)
                .favorites(favorites)
                .tripTypes(tripTypes)
                .build();
    }
}
