package com.example.model.gasStation;

import com.example.model.Service;
import com.example.model.client.Client;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;
import org.example.model.GasStation;
import org.example.model.TrafficCenter;
import org.example.useCases.create.CreateGasStation;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class GasStationService implements Service<GasStation, CreateGasStation> {
    private static final GasStationService INSTANCE = new GasStationService();

    public static GasStationService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<GasStation> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final GasStation[] gasStations = gson.fromJson(stringHttpResponse.body(), GasStation[].class);
        return List.of(gasStations);
    }

    @Override
    public GasStation addItem(CreateGasStation itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/gas-station";
    }
}
