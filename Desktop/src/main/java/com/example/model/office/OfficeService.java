package com.example.model.office;

import com.example.model.Service;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.model.Office;

import java.util.List;


public class OfficeService implements Service<Office, CreateOfficeRequest> {

    private final static OfficeService instance = new OfficeService();

    public static OfficeService getInstance() {
        return instance;
    }

    @Override
    public List<Office> getItems(Integer page, Integer size) {
        return null;
    }

    @Override
    public Office addItem(CreateOfficeRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/office";
    }
}
