package com.tago.domain.member.domain.vo;

import com.tago.domain.member.domain.MemberTag;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class MemberTags {

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTag> memberTags = new ArrayList<>();

    public void addMemberTags(List<MemberTag> memberTags) {
        this.memberTags.addAll(memberTags);
    }
}
