package org.project.personal.identity_provider.utils;

import org.project.personal.identity_provider.dto.request.JoinRequest;
import org.project.personal.identity_provider.dto.request.UserEmail;
import org.project.personal.identity_provider.entity.User;
import org.springframework.test.util.ReflectionTestUtils;

public class UserTestUtils {

    public static JoinRequest getJoinRequest(){
        JoinRequest joinRequest = new JoinRequest();
        /**
         *     private String email;
         *     private String userName;
         *     private String nickName;
         *     private String password;
         *
         *     {
         *     "email" : "test@email.com",
         *     "userName" : "test_user",
         *     "nickName" : "nickName",
         *     "password" : "password"
         *      }
         */

        ReflectionTestUtils.setField(joinRequest, "email", "test@email.com");
        ReflectionTestUtils.setField(joinRequest, "userName","test_user");
        ReflectionTestUtils.setField(joinRequest, "password","password");

        return joinRequest;
    }

    public static User getUser(){
        JoinRequest joinRequest = getJoinRequest();

        UserEmail email = UserEmailUtils.getUserEmail(joinRequest.getEmail());

        return User.builder()
                .userName(joinRequest.getUserName())
                .emailLocal(email.getLocal())
                .emailDomain(email.getDomain())
                .password(joinRequest.getPassword())
                .build();
    }

}
