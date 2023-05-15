package org.project.personal.identity_provider.utils;

import org.project.personal.identity_provider.dto.request.UserEmail;

import java.util.StringTokenizer;

public class UserEmailUtils {

    private UserEmailUtils() {}

    public static UserEmail getUserEmail(String  email) {
        StringTokenizer st = new StringTokenizer(email, "@");

        return UserEmail.builder()
                .local(st.nextToken())
                .domain(st.nextToken())
                .build();
    }

}

