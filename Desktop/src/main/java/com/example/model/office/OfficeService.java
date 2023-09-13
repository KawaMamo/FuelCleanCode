package com.example.model.office;

import com.example.model.Service;
import com.example.model.office.response.OfficeResponseEntity;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.model.Office;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;


public class OfficeService implements Service<Office, CreateOfficeRequest, UpdateOfficeRequest> {

    private final static OfficeService instance = new OfficeService();

    public static OfficeService getInstance() {
        return instance;
    }

    @Override
    public List<Office> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final Office[] offices = gson.fromJson(stringHttpResponse.body(), Office[].class);
        return List.of(offices);
    }

    @Override
    public Office addItem(CreateOfficeRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Office.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/office";
    }

    @Override
    public Office getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), OfficeResponseEntity.class)._embedded.officeList.get(0);
    }

    @Override
    public Office editItem(UpdateOfficeRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Office.class);
    }
}
