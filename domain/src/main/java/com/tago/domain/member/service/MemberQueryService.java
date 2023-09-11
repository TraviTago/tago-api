package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
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

    public Member findByMemberId(Long memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member findByPhone(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Optional<Member> findOptionalByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public boolean existsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }
}
