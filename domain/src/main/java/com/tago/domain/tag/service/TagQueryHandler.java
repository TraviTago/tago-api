package com.tago.domain.tag.service;

import com.tago.domain.common.error.ErrorCode;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.exception.TagNotFoundException;
import com.tago.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagQueryHandler {

    private final TagRepository tagRepository;

    public Tag findByType(Favorite favorite) {
        return tagRepository.findByType(favorite)
                .orElseThrow(TagNotFoundException::new);
    }
}
