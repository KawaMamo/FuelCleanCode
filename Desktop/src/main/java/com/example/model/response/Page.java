package com.example.model.response;

import lombok.Data;

@Data
public class Page {
    private int size;
    private int totalElements;
    private int totalPages;
    private int number;
}