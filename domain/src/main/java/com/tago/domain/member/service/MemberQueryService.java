package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;
import com.tago.domain.member.exception.MemberNotFoundException;
import com.tago.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public Member getMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMember(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getOrCreateMember(String email, OAuthProvider oauthProvider, String name) {
        return memberRepository.findByEmailAndOauthProvider(email, oauthProvider)
                .orElseGet(() -> createMember(email, oauthProvider, name));
    }

    private Member createMember(String email, OAuthProvider oAuthProvider, String name) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .oauthProvider(oAuthProvider)
                .authority(Authority.USER)
                .build();
        return memberRepository.save(member);
    }
}
