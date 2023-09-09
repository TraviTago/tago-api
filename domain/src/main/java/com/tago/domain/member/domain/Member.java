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
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniqueAccount", columnNames = {"email", "oauth_provider"})
})
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "oauth_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private OAuthProvider oauthProvider;

    @Embedded
    private Profile profile;

    @Embedded
    private MemberTags memberTags;

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

    public List<Favorite> getMemberTags() {
        return memberTags.getMemberTags().stream()
                .map(memberTag -> memberTag.getTag().getType())
                .toList();
    }

    public List<TripType> getTripTypes() {
        return profile.getTripTypes();
    }
}
