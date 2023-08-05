package com.tago.domain.member.repository;


import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByEmailAndOauthProvider(String email, OAuthProvider oAuthProvider);
}