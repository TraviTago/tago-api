package com.tago.domain.tag.repository;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;

import java.util.List;

public interface TagCustomRepository {
    List<Tag> findAllByType(List<Favorite> types);
}
