package org.project.personal.identity_provider.service;

import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.MemberPasswordModifyRequest;
import org.project.personal.identity_provider.entity.Member;

public interface MemberService {
    Member registerMember(JoinRequest joinRequest);

    Member searchMemberByMemberId(Long id);

    Member modifyPassword(Long id, MemberPasswordModifyRequest memberModifyRequest);

    void deleteMember(Long id);
}
