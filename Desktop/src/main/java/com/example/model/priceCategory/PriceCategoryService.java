package com.example.model.priceCategory;

import com.example.model.Service;
import com.example.model.priceCategory.response.PriceCategoryResponseEntity;
import com.google.gson.Gson;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.model.PriceCategory;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PriceCategoryService implements Service<PriceCategory, CreatePriceCategoryRequest, UpdatePriceCategoryRequest> {
    private final static PriceCategoryService instance = new PriceCategoryService();

    public static PriceCategoryService getInstance(){
        return instance;
    }
    @Override
    public List<PriceCategory> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), PriceCategory[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final PriceCategoryResponseEntity priceCategoryResponseEntity = gson.fromJson(stringHttpResponse.body(), PriceCategoryResponseEntity.class);
            if(Objects.nonNull(priceCategoryResponseEntity._embedded))
                return priceCategoryResponseEntity._embedded.priceCategoryList;
        }
        return new ArrayList<>();
    }

    public List<PriceCategory> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+""+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PriceCategoryResponseEntity priceCategoryResponseEntity = gson.fromJson(stringHttpResponse.body(), PriceCategoryResponseEntity.class);
        if(Objects.nonNull(priceCategoryResponseEntity._embedded))
            return priceCategoryResponseEntity._embedded.priceCategoryList;
        return new ArrayList<>();
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

    @Override
    public PriceCategory getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), PriceCategoryResponseEntity.class)._embedded.priceCategoryList.get(0);
    }

    @Override
    public PriceCategory delete(Long id) {
        return null;
    }

    @Override
    public PriceCategory editItem(UpdatePriceCategoryRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), PriceCategory.class);
    }

    private static String getPayload(CreatePriceCategoryRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("name", request.getName());
        return new Gson().toJson(payloadObj);
    }
}
