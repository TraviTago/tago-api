package com.tago.domain.tag.repository.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.repository.TagCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.tago.domain.tag.domain.QTag.tag;

@Repository
@RequiredArgsConstructor
public class TagCustomRepositoryImpl implements TagCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Tag> findByTypes(List<Favorite> types) {
        return queryFactory.selectFrom(tag)
                .where(typeIn(types))
                .fetch();
    }

    private BooleanExpression typeIn(List<Favorite> types) {
        return tag.type.in(types);
    }
}
