package org.nestech.identityprovider.user.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPassRequest {
    private Integer id;
    private String oldPassword;
    @Size(min = 8, max = 16, message = "password should be at least 8 characters")
    private String newPassword;
}
