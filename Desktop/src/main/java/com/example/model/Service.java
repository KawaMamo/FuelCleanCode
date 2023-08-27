package com.example.model;

import com.example.model.client.Client;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;

import java.util.List;

public interface Service<T, U> {
    Client client = Client.getInstance("http://localhost:8081/");
    Gson gson = AppGson.getGson();

    List<T> getItems(Integer page, Integer size);
    T addItem(U itemRequest);
    String getEndPoint();
}
