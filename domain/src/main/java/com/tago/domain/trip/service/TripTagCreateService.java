package com.tago.domain.trip.service;

import com.tago.domain.member.domain.vo.Favorite;
import com.tago.domain.tag.domain.Tag;
import com.tago.domain.tag.handler.TagQueryService;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.TripTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TripTagCreateService {

    private final TagQueryService tagQueryService;

    public List<TripTag> createTripTags(Trip trip, List<Favorite> types) {
        List<Tag> tags = tagQueryService.findByTypes(types);

        return tags.stream()
                .map(tag -> new TripTag(trip, tag))
                .toList();
    }
}
