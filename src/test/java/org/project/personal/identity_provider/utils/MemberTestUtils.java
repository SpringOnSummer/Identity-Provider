package org.project.personal.identity_provider.utils;

import org.project.personal.identity_provider.domain.MemberEmail;
import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.entity.Member;
import org.project.personal.identity_provider.entity.MemberDetail;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;

public class MemberTestUtils {

    public static JoinRequest getJoinRequest(){
        JoinRequest joinRequest = new JoinRequest();
        /**
         *     private String email;
         *     private String memberName;
         *     private String password;
         *     private String phoneNumber;
         *     private String nickName;
         *
         *     {
         *     "email" : "test@email.com",
         *     "memberName" : "test_member",
         *     "password" : "password",
         *     "phoneNumber" : "010-1234-5678",
         *     "nickName" : "nickName"
         *      }
         */

        ReflectionTestUtils.setField(joinRequest, "email", "test@email.com");
        ReflectionTestUtils.setField(joinRequest, "memberName","test_member");
        ReflectionTestUtils.setField(joinRequest, "password","password");

        ReflectionTestUtils.setField(joinRequest, "phoneNumber","010-1234-5678");
        ReflectionTestUtils.setField(joinRequest, "nickName","nickName");

        return joinRequest;
    }

    public static Member getMember(){
        JoinRequest joinRequest = getJoinRequest();

        MemberEmail email = MembersEmailUtils.convertEmail(joinRequest.getEmail());

        return Member.builder()
                .memberName(joinRequest.getMemberName())
                .emailLocal(email.getLocal())
                .emailDomain(email.getDomain())
                .password(joinRequest.getPassword())
                .build();
    }

    public static MemberDetail getMemberDetail(){
        JoinRequest joinRequest = getJoinRequest();

        return MemberDetail.builder()
                .nickName(joinRequest.getNickName())
                .phoneNumber(joinRequest.getPhoneNumber())
                .joinedAt(LocalDateTime.now())
                .build();

    }

}
