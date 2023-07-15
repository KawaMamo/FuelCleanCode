package org.example.model;

import lombok.Data;

@Data
public abstract class Place {
    private Long id;
    private String placeType;
}
