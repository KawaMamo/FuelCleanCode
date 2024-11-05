package com.example.model.office;

import com.example.model.Service;
import com.example.model.office.response.OfficeResponseEntity;
import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.model.Office;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
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
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Office[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final OfficeResponseEntity officeResponseEntity = gson.fromJson(stringHttpResponse.body(), OfficeResponseEntity.class);
            if(Objects.nonNull(officeResponseEntity._embedded))
                return officeResponseEntity._embedded.officeList;
        }
        return new ArrayList<>();
    }

    public List<Office> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final OfficeResponseEntity officeResponseEntity = gson.fromJson(stringHttpResponse.body(), OfficeResponseEntity.class);
        if(Objects.nonNull(officeResponseEntity._embedded))
            return officeResponseEntity._embedded.officeList;
        return new ArrayList<>();
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
    public Office delete(Long id) {
        return null;
    }

    @Override
    public Office editItem(UpdateOfficeRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Office.class);
    }


}
