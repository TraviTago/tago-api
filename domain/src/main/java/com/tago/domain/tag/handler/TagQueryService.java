package com.tago.domain.tag.handler;

import com.tago.domain.tag.domain.vo.TagType;
import com.tago.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagQueryService {

    private final TagRepository tagRepository;

    public List<com.tago.domain.tag.domain.Tag> findByTypes(List<TagType> types) {
        return tagRepository.findByTypes(types);
    }
}
