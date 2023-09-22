package com.tago.domain.member.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.QMember;
import com.tago.domain.member.domain.QMemberTag;
import com.tago.domain.member.repository.MemberCustomRepository;
import com.tago.domain.tag.domain.QTag;
import com.tago.domain.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.tago.domain.member.domain.QMember.member;
import static com.tago.domain.member.domain.QMemberTag.memberTag;
import static com.tago.domain.tag.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findByMemberId(Long memberId) {
        return Optional.ofNullable(queryFactory.selectFrom(member)
                .leftJoin(member.memberTags, memberTag).fetchJoin()
                .leftJoin(memberTag.tag, tag).fetchJoin()
                .where(memberIdEq(memberId))
                .fetchOne());
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return member.id.eq(memberId);
    }
}
