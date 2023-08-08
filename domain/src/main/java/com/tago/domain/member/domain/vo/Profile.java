package com.tago.domain.member.domain.vo;

import com.tago.domain.common.converter.FavoriteEnumArrayConverter;
import com.tago.domain.common.converter.TripTypeEnumArrayConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.Builder.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Profile {

    @Column(name = "age_range")
    private Integer ageRange;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Mbti mbti;

    @Default
    @Convert(converter = FavoriteEnumArrayConverter.class)
    private List<Favorite> favorites = new ArrayList<>();

    @Default
    @Column(name = "trip_types")
    @Convert(converter = TripTypeEnumArrayConverter.class)
    private List<TripType> tripTypes = new ArrayList<>();

    public boolean isSignedUp() {
        return ageRange != null || gender != null || mbti != null;
    }
}
