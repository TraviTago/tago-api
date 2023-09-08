package com.tago.api.member.application;

import com.tago.api.common.mapper.MemberDtoMapper;
import com.tago.api.member.dto.request.EditMemberInfoRequest;
import com.tago.api.member.dto.response.MemberInfoResponse;
import com.tago.domain.member.domain.Member;
import com.tago.domain.member.dto.MemberInfoDto;
import com.tago.domain.member.service.MemberQueryService;
import com.tago.domain.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberQueryService memberQueryService;
    private final MemberUpdateService memberUpdateService;


    @Transactional(readOnly = true)
    public MemberInfoResponse getInfo(Long memberId) {
        Member member = memberQueryService.findById(memberId);
        return MemberDtoMapper.toDto(member);
    }

    @Transactional(readOnly = true)
    public void editMemberInfo(Long memberId, EditMemberInfoRequest request) {
        MemberInfoDto dto = MemberInfoDto.builder()
                .mbti(request.getMbti())
                .favorites(request.getFavorites())
                .tripTypes(request.getTripTypes())
                .build();
        memberUpdateService.updateMemberInfo(memberId, dto);
    }


}
