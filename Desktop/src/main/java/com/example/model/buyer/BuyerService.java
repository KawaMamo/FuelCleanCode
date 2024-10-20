package com.example.model.buyer;

import com.example.model.Service;
import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.model.Buyer;

import java.util.List;

public class BuyerService implements Service<Buyer, CreateBuyerRequest, UpdateBuyerRequest> {
    private static final BuyerService INSTANCE = new BuyerService();
    public static BuyerService getInstance() {return INSTANCE;}
    @Override
    public List<Buyer> getItems(Integer page, Integer size) {
        return List.of(new Buyer());
    }

    @Override
    public Buyer addItem(CreateBuyerRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return null;
    }

    @Override
    public Buyer getItem(Long id) {
        return null;
    }

    @Override
    public Buyer delete(Long id) {
        return null;
    }

    @Override
    public Buyer editItem(UpdateBuyerRequest updateRequest) {
        return null;
    }
}
