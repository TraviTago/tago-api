package com.tago.domain.member.service;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.MemberTag;
import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.service.TagQueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTagCreateService {

    private final TagQueryHandler tagQueryHandler;

    public List<MemberTag> createMemberTags(Member member, List<Favorite> types) {
        List<Tag> tags = tagQueryHandler.findAllByType(types);
        return tags.stream()
                .map(tag -> new MemberTag(member, tag))
                .toList();
    }
}
