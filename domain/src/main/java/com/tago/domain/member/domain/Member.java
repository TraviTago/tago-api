package com.tago.domain.member.domain;


import com.tago.domain.member.domain.vo.*;
import com.tago.domain.member.domain.vo.MemberTags;
import jakarta.persistence.*;
import lombok.*;

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
    private Authority authority;

    @Embedded
    private Profile profile;

    @Builder.Default
    @Embedded
    private MemberTags memberTags = new MemberTags();

    public void updateInfo(int ageRange, Gender gender, Mbti mbti,
                           List<TripType> tripTypes, List<MemberTag> tags) {
        updateMemberTags(tags);
        updateProfile(ageRange, gender, mbti, tripTypes);
    }

    private void updateMemberTags(List<MemberTag> tags) {
        this.memberTags.getMemberTags().clear();
        this.memberTags.getMemberTags().addAll(tags);
    }

    private void updateProfile(int ageRange, Gender gender, Mbti mbti, List<TripType> tripTypes) {
        this.profile = Profile.builder()
                .ageRange(ageRange)
                .gender(gender)
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
        this.memberTags.addMemberTags(memberTags);
    }

    public List<Favorite> getMemberTags() {
        return memberTags.getMemberTags().stream()
                .map(memberTag -> memberTag.getTag().getType())
                .toList();
    }
}
