package org.nestech.identityprovider.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignToEmployeeRequest {

    private Integer id;
    private Integer employeeId;

}
