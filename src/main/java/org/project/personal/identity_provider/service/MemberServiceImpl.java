package org.project.personal.identity_provider.service;

import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.MemberEmail;
import org.project.personal.identity_provider.dto.request.MemberPasswordModifyRequest;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.repository.MemberRepository;
import org.project.personal.identity_provider.utils.MembersEmailUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member registerMember(JoinRequest joinRequest) {

        MemberEmail email = MembersEmailUtils.convertEmail(joinRequest.getEmail());

        Member member = Member.builder()
                .memberName(joinRequest.getMemberName())
                .emailLocal(email.getLocal())
                .emailDomain(email.getDomain())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .build();

        memberRepository.save(member);

        return member;
    }

    public Member searchMemberByMemberId(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public Member modifyPassword(Long id, MemberPasswordModifyRequest memberModifyRequest) {

        Member member = memberRepository.findById(id).orElseThrow();

        if (passwordEncoder.matches(memberModifyRequest.getCurrentPassword(), member.getPassword())) {
            member.modifyPassword(passwordEncoder.encode(memberModifyRequest.getResetPassword()));
        }

        return member;
    }


    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
