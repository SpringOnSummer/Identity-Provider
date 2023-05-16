package org.project.personal.identity_provider.utils;

import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.MemberEmail;
import org.project.personal.identity_provider.entity.Member;
import org.springframework.test.util.ReflectionTestUtils;

public class MemberTestUtils {

    public static JoinRequest getJoinRequest(){
        JoinRequest joinRequest = new JoinRequest();
        /**
         *     private String email;
         *     private String memberName;
         *     private String nickName;
         *     private String password;
         *
         *     {
         *     "email" : "test@email.com",
         *     "memberName" : "test_member",
         *     "nickName" : "nickName",
         *     "password" : "password"
         *      }
         */

        ReflectionTestUtils.setField(joinRequest, "email", "test@email.com");
        ReflectionTestUtils.setField(joinRequest, "memberName","test_member");
        ReflectionTestUtils.setField(joinRequest, "password","password");

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

}
