package com.tago.domain.member.repository;

import com.tago.domain.member.domain.Member;

import java.util.Optional;

public interface MemberCustomRepository {

    Optional<Member> findByMemberId(Long memberId);
}
