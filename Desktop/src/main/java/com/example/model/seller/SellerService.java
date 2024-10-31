package com.example.model.seller;

import com.example.model.Service;
import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.model.Seller;

import java.util.List;

public class SellerService implements Service<Seller, CreateSellerRequest, UpdateSellerRequest> {
    private static final SellerService INSTANCE = new SellerService();
    public static SellerService getInstance() {return INSTANCE;}

    @Override
    public List<Seller> getItems(Integer page, Integer size) {
        return null;
    }

    @Override
    public Seller addItem(CreateSellerRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return null;
    }

    @Override
    public Seller getItem(Long id) {
        return null;
    }

    @Override
    public Seller delete(Long id) {
        return null;
    }

    @Override
    public Seller editItem(UpdateSellerRequest updateRequest) {
        return null;
    }
}
