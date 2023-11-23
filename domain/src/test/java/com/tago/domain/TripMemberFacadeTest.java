package com.tago.domain;


import static org.assertj.core.api.Assertions.assertThat;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Gender;
import com.tago.domain.member.domain.vo.Mbti;
import com.tago.domain.member.domain.vo.Profile;
import com.tago.domain.member.domain.vo.Role;
import com.tago.domain.member.repository.MemberRepository;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.repository.TripRepository;
import com.tago.domain.tripmember.dto.TripMemberDto;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import com.tago.domain.tripmember.service.TripMemberFacade;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("mysql")
@SpringBootTest
public class TripMemberFacadeTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripMemberRepository tripMemberRepository;

    @Autowired
    private TripMemberFacade tripMemberFacade;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    public void after() {
        tripRepository.deleteAll();
        memberRepository.deleteAll();
    }

    /**
     * GIVEN 최대 인원 수 10명인 여행 생성 및 여행에 참여하려는 멤버 10명 생성
     * WHEN 10명의 멤버가 동시에 여행 참여 요청
     * THEN 여행 참여 인원 수 10, 해당 여행 멤버 수 10
     */
    @Test
    void 동시에_10명_참여하기_요청() throws InterruptedException {
        Trip mockTrip = createMockTrip(10, 0);

        concurrencyTest(() -> {
            Member member = createMockMember();
            tripMemberFacade.createOrDelete(mockTrip.getId(), member.getId(), "ACCEPT");
        }, 10);

        Trip trip = tripRepository.findById(mockTrip.getId()).orElseThrow();
        List<TripMemberDto> tripMembers = tripMemberRepository.findMembersByTrip(mockTrip);

        assertThat(trip.getCurrentCnt()).isEqualTo(10);
        assertThat(tripMembers.size()).isEqualTo(10);
    }

    /**
     * GIVEN 여행 및 여행에 참여하려는 멤버 생성
     * WHEN 같은 멤버가 동시에 여행 참여 중복 요청 (더블 클릭과 같은 이유로)
     * THEN 여행 중복 참여가 불가능하므로, 한 개의 참여 요청만 수락
     */
    @Test
    void 같은_멤버가_여행_참여하기_중복_요청() throws InterruptedException {
        Trip mockTrip = createMockTrip(10, 0);
        Member mockMember = createMockMember();

        concurrencyTest(() -> {
            tripMemberFacade.createOrDelete(mockTrip.getId(), mockMember.getId(), "ACCEPT");
        }, 2);

        Trip trip = tripRepository.findById(mockTrip.getId()).orElseThrow();
        List<TripMemberDto> tripMembers = tripMemberRepository.findMembersByTrip(mockTrip);

        assertThat(trip.getCurrentCnt()).isEqualTo(1);
        assertThat(tripMembers.size()).isEqualTo(1);
    }

    /**
     * GIVEN 정원이 1명 남은 여행 생성 및 여행에 참여하려는 멤버 2명 생성
     * WHEN 2명의 멤버가 동시에 여행 참여 요청
     * THEN 한 명의 여행 참여 요청만 수락
     */
    @Test
    void 한자리_남은_여행에_2명이_동시에_참여하기_요청() throws InterruptedException {
        Trip mockTrip = createMockTrip(1, 0);

        concurrencyTest(() -> {
            Member member = createMockMember();
            tripMemberFacade.createOrDelete(mockTrip.getId(), member.getId(), "ACCEPT");
        }, 2);

        Trip trip = tripRepository.findByIdFetchTripMember(mockTrip.getId()).orElseThrow();
        List<TripMemberDto> tripMembers = tripMemberRepository.findMembersByTrip(mockTrip);

        assertThat(trip.getCurrentCnt()).isEqualTo(1);
        assertThat(tripMembers.size()).isEqualTo(1);
    }

    private void concurrencyTest(Runnable runnable, int threadCount) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    runnable.run();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
    }

    private Member createMockMember() {
        Profile profile = Profile.builder()
                .imgUrl("test")
                .ageRange(20)
                .gender(Gender.MALE)
                .mbti(Mbti.ENFJ)
                .tripTypes(List.of())
                .build();
        Member member = Member.builder()
                .phoneNumber(UUID.randomUUID().toString())
                .name("이하늘")
                .role(Role.USER)
                .profile(profile)
                .build();
        return memberRepository.save(member);
    }

    private Trip createMockTrip(int maxCnt, int currentCnt) {
        Trip trip = Trip.builder()
                .name("회먹고 곱창먹는 여행")
                .dateTime(LocalDateTime.now())
                .meetPlace("해운대")
                .totalTime(8)
                .maxCnt(maxCnt)
                .currentCnt(currentCnt)
                .condition(null)
                .member(null)
                .origin(false)
                .build();
        return tripRepository.save(trip);
    }
}
