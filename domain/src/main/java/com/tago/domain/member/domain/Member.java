package com.tago.domain.member.domain;


import com.tago.domain.member.domain.vo.*;
import com.tago.domain.tag.domain.vo.TagType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTag> memberTags = new ArrayList<>();

    public void updateInfo(Mbti mbti, String imgUrl, List<TripType> tripTypes, List<MemberTag> tags) {
        updateMemberTags(tags);
        updateProfile(mbti, imgUrl, tripTypes);
    }

    private void updateMemberTags(List<MemberTag> tags) {
        this.memberTags.clear();
        this.memberTags.addAll(tags);
    }

    private void updateProfile(Mbti mbti, String imgUrl, List<TripType> tripTypes) {
        this.profile = Profile.builder()
                .ageRange(this.getAgeRange())
                .gender(this.getGender())
                .imgUrl(imgUrl)
                .mbti(mbti)
                .tripTypes(tripTypes)
                .build();
    }

    public boolean isSignUp() {
        return profile.isSignedUp();
    }

    public Integer getAgeRange() {
        return profile.getAgeRange();
    }

    public Gender getGender() {
        return profile.getGender();
    }

    public Mbti getMbti() {
        return profile.getMbti();
    }

    public String getImgUrl() {
        return profile.getImgUrl();
    }

    public List<TripType> getTripTypes() {
        return profile.getTripTypes();
    }

    public void addMemberTags(List<MemberTag> memberTags) {
        this.memberTags.addAll(memberTags);
    }

    public List<TagType> getMemberTags() {
        return memberTags.stream()
                .map(memberTag -> memberTag.getTag().getType())
                .toList();
    }
}
