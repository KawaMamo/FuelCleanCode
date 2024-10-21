package com.example.model.buyer;

import com.example.model.Service;
import com.example.model.buyer.response.BuyerResponseEntity;
import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.model.Buyer;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BuyerService implements Service<Buyer, CreateBuyerRequest, UpdateBuyerRequest> {
    private static final BuyerService INSTANCE = new BuyerService();
    public static BuyerService getInstance() {return INSTANCE;}
    @Override
    public List<Buyer> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final Buyer[] buyers = gson.fromJson(stringHttpResponse.body(),
                    Buyer[].class);
            return List.of(buyers);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final BuyerResponseEntity buyerResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    BuyerResponseEntity.class);
            if(Objects.nonNull(buyerResponseEntity._embedded))
                return buyerResponseEntity._embedded.buyerArrayList;
        }
        return new ArrayList<>();
    }

    public List<Buyer> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final BuyerResponseEntity buyerResponseEntity = gson.fromJson(stringHttpResponse.body(),
                BuyerResponseEntity.class);
        if(Objects.nonNull(buyerResponseEntity._embedded))
            return buyerResponseEntity._embedded.buyerArrayList;
        return new ArrayList<>();
    }

    @Override
    public Buyer addItem(CreateBuyerRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Buyer.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/buyer";
    }

    @Override
    public Buyer getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), BuyerResponseEntity.class)._embedded.buyerArrayList.get(0);
    }

    @Override
    public Buyer delete(Long id) {
        return null;
    }

    @Override
    public Buyer editItem(UpdateBuyerRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Buyer.class);
    }
}
