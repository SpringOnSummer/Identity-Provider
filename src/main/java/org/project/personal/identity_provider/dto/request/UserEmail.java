package org.project.personal.identity_provider.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserEmail {
    private String local;
    private String domain;

    @Builder
    public UserEmail(String local, String domain) {
        this.local = local;
        this.domain = domain;
    }
}
