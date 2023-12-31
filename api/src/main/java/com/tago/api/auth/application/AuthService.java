package com.tago.api.auth.application;

import com.tago.api.auth.dto.request.LoginRequest;
import com.tago.api.auth.dto.request.SignUpRequest;
import com.tago.api.auth.dto.response.LoginResponse;
import com.tago.api.auth.dto.response.SignUpResponse;
import com.tago.api.auth.event.AuthEvent;
import com.tago.api.auth.event.producer.AuthEventProducer;
import com.tago.api.common.security.jwt.JwtTokenPublisher;
import com.tago.api.common.exception.AlreadyExistsAccountException;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.domain.vo.Role;
import com.tago.domain.member.service.MemberCreateService;
import com.tago.domain.member.handler.MemberQueryService;
import com.tago.domain.trip.repository.TripRepository;
import com.tago.domain.tripmember.repository.TripMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberQueryService memberQueryService;
    private final MemberCreateService memberCreateService;
    private final JwtTokenPublisher jwtTokenPublisher;
    private final AuthEventProducer authEventProducer;
    private final TripMemberRepository tripMemberRepository;
    private final TripRepository tripRepository;

    @Transactional
    public LoginResponse login(LoginRequest request){
        Member member = memberQueryService.findByPhone(request.getNumber());
        authEventProducer.produceEvent(new AuthEvent(member.getPhoneNumber(), request.getFirebaseToken()));
        return new LoginResponse(jwtTokenPublisher.generateTokens(member.getId(), member.getRole()));
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest request) {
        checkExistsPhoneNumber(request.getNumber());
        Member member = memberCreateService.create(request.toMemberInfoDto());
        authEventProducer.produceEvent(new AuthEvent(member.getPhoneNumber(), request.getFirebaseToken()));

        return new SignUpResponse(
                member.getId(),
                member.getRole(),
                jwtTokenPublisher.generateTokens(member.getId(), member.getRole())
        );
    }

    @Transactional
    public void withdraw(Long memberId) {

        if(tripRepository.existsByMemberId(memberId)) {
            throw new IllegalStateException("멤버가 여행에 참여 중이므로 탈퇴할 수 없습니다.");
        }

        Member member = memberQueryService.findById(memberId);
        tripMemberRepository.deleteByMemberId(memberId);
        memberQueryService.delete(member);

    }

    private void checkExistsPhoneNumber(String number) {
        memberQueryService.findOptionalByPhoneNumber(number)
                .ifPresent(member -> {throw new AlreadyExistsAccountException();});
    }
}
