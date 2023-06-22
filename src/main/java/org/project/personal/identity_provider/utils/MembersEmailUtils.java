package org.project.personal.identity_provider.utils;

import org.project.personal.identity_provider.domain.MemberEmail;

import java.util.StringTokenizer;

public class MembersEmailUtils {

    private MembersEmailUtils() {}

    public static MemberEmail convertEmail(String  email) {
        StringTokenizer st = new StringTokenizer(email, "@");

        return MemberEmail.builder()
                .local(st.nextToken())
                .domain(st.nextToken())
                .build();
    }

}

