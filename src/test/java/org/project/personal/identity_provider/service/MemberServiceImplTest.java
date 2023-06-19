package org.project.personal.identity_provider.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.MemberPasswordModifyRequest;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.repository.MemberRepository;
import org.project.personal.identity_provider.utils.MemberTestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    MemberServiceImpl memberService;

    Member member;
    JoinRequest joinRequest;

    @BeforeEach
    void SetUp() {
        joinRequest = MemberTestUtils.getJoinRequest();
        member = MemberTestUtils.getMember();
    }

    @Test
    void registerMember() {

        given(memberRepository.save(any(Member.class))).willReturn(member);
        given(passwordEncoder.encode(joinRequest.getPassword())).willReturn(member.getPassword());

        Member actual = memberService.registerMember(joinRequest);

        assertThat(actual.getEmailLocal(), equalTo(member.getEmailLocal()));
    }

    @Test
    void searchMemberByMemberId() {
        Member member = MemberTestUtils.getMember();

        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));

        Member actual = memberService.searchMemberByMemberId(member.getId());

        then(memberRepository)
                .should()
                .findById(member.getId());

    }

    @Test
    void modifyPassword() {
        MemberPasswordModifyRequest request = MemberTestUtils.getMemberPasswordModifyRequest();
        PasswordEncoder tempPasswordEncoder = new BCryptPasswordEncoder();

        given(memberRepository.findById(member.getId())).willReturn(Optional.of(member));
        given(passwordEncoder.matches(anyString(), anyString())).willReturn(true);
        given(passwordEncoder.encode(request.getResetPassword())).willReturn(tempPasswordEncoder.encode(request.getResetPassword()));

        Member actual = memberService.modifyPassword(member.getId(), request);

        assertThat(actual.getPassword(), equalTo(member.getPassword()));
    }

    @Test
    void deleteMember() {

        willDoNothing().given(memberRepository).deleteById(member.getId());

        memberService.deleteMember(member.getId());

        then(memberRepository)
                .should()
                .deleteById(member.getId());
    }
}