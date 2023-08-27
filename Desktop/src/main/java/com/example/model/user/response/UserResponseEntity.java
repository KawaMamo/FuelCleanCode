package com.example.model.user.response;

import com.example.model.response.Links;
import com.example.model.response.Page;
import lombok.Data;

@Data
public class UserResponseEntity {
    private Embedded _embedded;
    private Links _links;
    private Page page;
}
