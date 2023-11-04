package com.example.model;

import com.example.model.client.Client;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;
import org.example.contract.request.update.UpdateGasStationRequest;

import java.net.http.HttpResponse;
import java.util.List;

import static com.example.model.properties.AppProperty.getProperties;

public interface Service<T, U, V> {
    Client client = Client.getInstance(getProperties().getProperty("fuel.service.host")+":"+ getProperties().getProperty("fuel.service.port") +"/");
    Gson gson = AppGson.getGson();

    List<T> getItems(Integer page, Integer size);
    T addItem(U itemRequest);
    String getEndPoint();
    T getItem(Long id);
    T delete(Long id);

    default HttpResponse<String> getResponse(Long id) {
        String getUrl = getEndPoint()+"?page=0&size=10&key=id&value="+ id +"&operation=%3A";
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        return stringHttpResponse;
    }

    T editItem(V updateRequest);

}
