package com.example.model.priceCategory;

import com.example.model.Service;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.model.PriceCategory;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PriceCategoryService implements Service<PriceCategory, CreatePriceCategoryRequest> {
    private final static PriceCategoryService instance = new PriceCategoryService();

    public static PriceCategoryService getInstance(){
        return instance;
    }
    @Override
    public List<PriceCategory> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PriceCategory[] priceCategories = gson.fromJson(stringHttpResponse.body(), PriceCategory[].class);
        return List.of(priceCategories);
    }

    @Override
    public PriceCategory addItem(CreatePriceCategoryRequest itemRequest) {
        final String payload = getPayload(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), PriceCategory.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/price-category";
    }

    private static String getPayload(CreatePriceCategoryRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("name", request.getName());
        return new Gson().toJson(payloadObj);
    }
}
