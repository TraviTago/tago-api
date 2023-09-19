package com.tago.domain.tag.handler;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagQueryService {

    private final TagRepository tagRepository;

    public List<Tag> findByTypes(List<Favorite> types) {
        return tagRepository.findByTypes(types);
    }
}
