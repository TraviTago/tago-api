package com.tago.api.trip.application;

import com.tago.api.common.dto.PageResponseDto;
import com.tago.api.common.mapper.TripDtoMapper;
import com.tago.api.common.mapper.TripStatusDtoMapper;
import com.tago.api.trip.dto.response.*;
import com.tago.domain.driver.repository.DispatchRepository;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.domain.TagoTrip;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.dto.TripPlaceDto;
import com.tago.domain.trip.handler.TripPlaceQueryService;
import com.tago.domain.trip.repository.TagoTripRepository;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.trip.handler.TripQueryService;
import com.tago.domain.tripmember.handler.TripMemberQueryService;
import jakarta.transaction.TransactionScoped;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripGetService {

    private final TripQueryService tripQueryService;
    private final MemberQueryService memberQueryService;
    private final TripPlaceQueryService tripPlaceQueryService;
    private final TripMemberQueryService tripMemberQueryService;
    private final DispatchRepository dispatchRepository;
    private final TagoTripRepository tagoTripRepository;

    @Transactional(readOnly = true)
    public PageResponseDto<TripGetResponse> getAll(
            Long cursorId, LocalDateTime cursorDate, int limit, Boolean sameGender, Boolean isPet, Long memberId
    ) {
        Member member = memberQueryService.findByIdFetchMemberTag(memberId);
        List<Trip> trips = tripQueryService.findAll(cursorId, cursorDate, limit, sameGender, isPet, member.getGender());
        List<TripGetResponse> dto = TripDtoMapper.toDto(trips, member);
        return PageResponseDto.from(dto);
    }

    @Transactional(readOnly = true)
    public TripStatusResponse getOneStatus(Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripMemberDto> tripMembers = tripMemberQueryService.findMembersByTrip(trip);
        return TripStatusDtoMapper.toDto(trip, tripMembers);
    }

    @Transactional(readOnly = true)
    public TripGetOneResponse getOne(Long memberId, Long tripId) {
        Trip trip = tripQueryService.findById(tripId);
        List<TripPlaceDto> tripPlaces = tripPlaceQueryService.findAll(trip);

        boolean isDispatched = dispatchRepository.existsByTripId(tripId);

        return new TripGetOneResponse(
                trip.getName(),
                trip.getCurrentCnt(),
                trip.getMaxCnt(),
                isJoined(tripId, memberId),
                tripPlaces,
                isDispatched
        );
    }

    @Transactional(readOnly = true)
    public MyTripGetResponse getAllByMember(Long memberId) {
        Member member = memberQueryService.findById(memberId);
        List<Trip> trips = tripQueryService.findAllByMember(member);
        return new MyTripGetResponse(TripDtoMapper.toDto(trips, member));
    }

    @Transactional(readOnly = true)
    public TripGetResponse recommend(Long memberId){
        Member member = memberQueryService.findByIdFetchMemberTag(memberId);
        Trip trips = tripQueryService.findByTripTag(memberId);
        return TripDtoMapper.getTripResponseDto(trips, member);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<TripGetResponse> search(String keyword, Long cursorId, LocalDateTime cursorDate, int limit, Long memberId) {
        Member member = memberQueryService.findById(memberId);
        List<Trip> trips = tripQueryService.searchByPlaceTitleKeyword(keyword, cursorId, cursorDate, limit);
        List<TripGetResponse> dto = TripDtoMapper.toDto(trips, member);
        return PageResponseDto.from(dto);
    }

//    @Transactional(readOnly = true)
//    public List<TagoTripResponse> getOriginTrips() {
//        List<TagoTrip> trips = tagoTripRepository.findAll();
//        return trips.stream()
//                .map(trip -> new TagoTripResponse(trip.getName(), trip.getImg_url()))
//                .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public TagoTripResponse getOriginTrips() {
        List<TagoTrip> trips = tagoTripRepository.findAll();
        List<TagoTripResponse.TagotripDTO> tagotrips = trips.stream()
                .map(trip -> new TagoTripResponse.TagotripDTO(trip.getName(), trip.getImg_url()))
                .collect(Collectors.toList());

        return new TagoTripResponse(tagotrips);
    }


    private Boolean isJoined(Long tripId, Long memberId) {
        return tripMemberQueryService.existsByTripIdAndMemberId(tripId, memberId);
    }
}
