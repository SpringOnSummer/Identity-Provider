package org.project.personal.identity_provider.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class JoinRequest {

    private String email;
    private String userName;
    private String password;

}
