package com.example.model.seller;

import com.example.model.Service;
import com.example.model.seller.response.SellerResponseEntity;
import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.model.Seller;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellerService implements Service<Seller, CreateSellerRequest, UpdateSellerRequest> {
    private static final SellerService INSTANCE = new SellerService();
    public static SellerService getInstance() {return INSTANCE;}

    @Override
    public List<Seller> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Seller[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final SellerResponseEntity sellerResponseEntity = gson.fromJson(stringHttpResponse.body(), SellerResponseEntity.class);
            if(Objects.nonNull(sellerResponseEntity._embedded))
                return sellerResponseEntity._embedded.sellerList;
        }
        return new ArrayList<>();
    }

    public List<Seller> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final SellerResponseEntity sellerResponseEntity = gson.fromJson(stringHttpResponse.body(), SellerResponseEntity.class);
        if(Objects.nonNull(sellerResponseEntity._embedded))
            return sellerResponseEntity._embedded.sellerList;
        return new ArrayList<>();
    }

    @Override
    public Seller addItem(CreateSellerRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Seller.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/seller";
    }

    @Override
    public Seller getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), SellerResponseEntity.class)._embedded.sellerList.get(0);
    }

    @Override
    public Seller delete(Long id) {
        return null;
    }

    @Override
    public Seller editItem(UpdateSellerRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Seller.class);
    }
}
