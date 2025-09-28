package com.example.model.transportation;

import com.example.model.Service;
import com.example.model.transportation.response.TransportationResponseEntity;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.model.Transportation;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransportationService implements Service<Transportation, CreateTransRequest, UpdateTransRequest> {
    private static final TransportationService INSTANCE = new TransportationService();

    public static TransportationService getInstance() {return INSTANCE;}
    @Override
    public List<Transportation> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Transportation[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final TransportationResponseEntity transportationResponseEntity = gson.fromJson(stringHttpResponse.body(), TransportationResponseEntity.class);
            if(Objects.nonNull(transportationResponseEntity._embedded))
                return transportationResponseEntity._embedded.transportationList;
        }
        return new ArrayList<>();
    }

    public List<Transportation> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TransportationResponseEntity transportationResponseEntity = gson.fromJson(stringHttpResponse.body(), TransportationResponseEntity.class);
        if(Objects.nonNull(transportationResponseEntity._embedded))
            return transportationResponseEntity._embedded.transportationList;
        return new ArrayList<>();
    }

    public List<Transportation> getItemsWithoutDocuments(Integer page, Integer size) {
        String getUrl = getEndPoint()+"/withoutDocuments?page="+page+"&size="+size;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TransportationResponseEntity transportationResponseEntity = gson.fromJson(stringHttpResponse.body(), TransportationResponseEntity.class);
        if(Objects.nonNull(transportationResponseEntity._embedded))
            return transportationResponseEntity._embedded.transportationList;
        return new ArrayList<>();
    }

    @Override
    public Transportation addItem(CreateTransRequest itemRequest) {
        final String json = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Transportation.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/trans";
    }

    @Override
    public Transportation getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), TransportationResponseEntity.class)._embedded.transportationList.get(0);
    }

    @Override
    public Transportation delete(Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelDelete(getEndPoint(), "/" + id);
        return gson.fromJson(stringHttpResponse.body(), Transportation.class);
    }

    @Override
    public Transportation editItem(UpdateTransRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Transportation.class);
    }

    public byte[] getRefineryReport(String exportType, String transType, LocalDate startDate, LocalDate endDate, Long id){

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/RefineryReport" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate + "/" + transType);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }

    public Transportation uploadImage(Long id, String fileUrl, String request){
        final HttpResponse<String> stringHttpResponse;
        try {
            stringHttpResponse = client.postMultiPart(getEndPoint() + "/" + id + "/document", request, fileUrl);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return gson.fromJson(stringHttpResponse.body(), Transportation.class);
    }


}
