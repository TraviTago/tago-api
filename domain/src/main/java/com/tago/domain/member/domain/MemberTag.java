package com.tago.domain.member.domain;

import com.tago.domain.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "member_tag")
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    public MemberTag(Member member, Tag tag) {
        this.member = member;
        this.tag = tag;
    }
}
