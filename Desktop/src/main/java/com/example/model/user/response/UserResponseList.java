package com.example.model.user.response;

import lombok.Data;

@Data
public class UserResponseList {
    private int id;
    private String email;
    private String role;
    private boolean locked;
}
