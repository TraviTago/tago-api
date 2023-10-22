package com.tago.domain;

import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Profile;
import com.tago.domain.member.domain.vo.Role;
import com.tago.domain.member.repository.MemberRepository;
import com.tago.domain.trip.domain.Trip;
import com.tago.domain.trip.domain.vo.Condition;
import com.tago.domain.trip.dto.OriginTripCreateDto;
import com.tago.domain.trip.repository.OriginTripRepository;
import com.tago.domain.trip.repository.TripRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Import({TestConfig.class})
@ActiveProfiles("mysql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DomainApplication.class)
@DataJpaTest
public class OriginTripBulkInsertTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OriginTripRepository originTripRepository;

    @Autowired
    private TripRepository tripRepository;


    @Test
    void bulkInsertTest() {
        Member member = memberRepository.save(Member.builder()
                .name("테스트")
                .phoneNumber("010-1111-1111")
                .role(Role.USER)
                .profile(Profile.builder().imgUrl("test").build())
                .build());

        List<OriginTripCreateDto> commands = new ArrayList<>();
        for(int i = 0; i < 10000; i++) {
            commands.add(createOriginTrip("바다 보러 갈래?", member.getId()));
        }

        long startTime = System.currentTimeMillis();
        originTripRepository.saveAllTrip(commands);
        long endTime = System.currentTimeMillis();

        System.out.println("execution time = " + (endTime - startTime) + "ms");
    }

    @Test
    void saveAllTest() {
        List<Trip> commands = new ArrayList<>();
        for(int i = 0; i < 10000; i++) {
            commands.add(createTrip());
        }

        long startTime = System.currentTimeMillis();
        tripRepository.saveAll(commands);
        long endTime = System.currentTimeMillis();

        System.out.println("execution time = " + (endTime - startTime) + "ms");
    }

    private OriginTripCreateDto createOriginTrip(String name, Long memberId) {
        return OriginTripCreateDto.builder()
                .name(name)
                .dateTime(LocalDateTime.now())
                .meetPlace("해운대역 4번출구")
                .totalTime(480)
                .maxCnt(4)
                .memberId(memberId)
                .places(null)
                .build();
    }

    private Trip createTrip() {
        Condition condition = Condition.builder()
                .sameGender(true)
                .sameAge(true)
                .isPet(true)
                .build();

        return Trip.builder()
                .name("바다 보러 갈래?")
                .dateTime(LocalDateTime.now())
                .meetPlace("해운대역 4번출구")
                .totalTime(480)
                .maxCnt(4)
                .currentCnt(0)
                .condition(condition)
                .member(null)
                .origin(true)
                .build();
    }


}
