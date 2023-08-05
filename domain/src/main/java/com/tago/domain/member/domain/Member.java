package com.tago.domain.member.domain;


import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private OAuthProvider oAuthProvider;

}
