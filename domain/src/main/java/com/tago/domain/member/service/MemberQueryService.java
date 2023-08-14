package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.OAuthProvider;
import com.tago.domain.member.exception.MemberNotFoundException;
import com.tago.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Optional<Member> findOptionalByEmailAndOauthProvider(String email, OAuthProvider oAuthProvider) {
        return memberRepository.findByEmailAndOauthProvider(email, oAuthProvider);
    }

    public boolean existsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }
}
