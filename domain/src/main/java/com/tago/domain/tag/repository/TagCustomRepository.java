package com.tago.domain.tag.repository;

import com.tago.domain.tag.domain.vo.TagType;

import java.util.List;

public interface TagCustomRepository {
    List<com.tago.domain.tag.domain.Tag> findByTypes(List<TagType> types);
}
