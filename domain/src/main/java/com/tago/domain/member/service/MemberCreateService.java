package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;
import com.tago.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {
    private final MemberRepository memberRepository;

    public Member create(String email, String name, OAuthProvider oAuthProvider) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .oauthProvider(oAuthProvider)
                .authority(Authority.USER)
                .build();
        return memberRepository.save(member);
    }
}
