package org.project.personal.identity_provider.dto.request;

import lombok.Getter;

@Getter
public class MemberPasswordModifyRequest {

    String currentPassword;
    String resetPassword;
}
