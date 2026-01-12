package org.nestech.identityprovider.user.request;

import lombok.Data;

@Data
public class UserRequest {

    private String email;
    private boolean locked;
}
