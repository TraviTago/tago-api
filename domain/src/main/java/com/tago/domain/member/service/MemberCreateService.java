package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Authority;
import com.tago.domain.member.domain.vo.OAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberCreateService {

    public final MemberQueryService memberQueryService;
    public final MemberCommandService memberCommandService;

    public Member getOrCreateMember(String email, String name, OAuthProvider oAuthProvider) {
        return memberQueryService.findOptionalByEmailAndOauthProvider(email, oAuthProvider)
                .orElseGet(() -> create(email, name, oAuthProvider));
    }

    private Member create(String email, String name, OAuthProvider oAuthProvider) {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .oauthProvider(oAuthProvider)
                .authority(Authority.USER)
                .build();
        return memberCommandService.save(member);
    }
}
