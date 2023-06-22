package org.project.personal.identity.provider.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEmail {
    private String local;
    private String domain;

    @Builder
    public MemberEmail(String local, String domain) {
        this.local = local;
        this.domain = domain;
    }
}
