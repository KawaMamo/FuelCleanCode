package com.example.model.gasStation;

import com.example.model.Service;
import com.example.model.gasStation.response.GasStationResponseEntity;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateGasStationRequest;
import org.example.contract.request.update.UpdateGasStationRequest;
import org.example.model.GasStation;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class GasStationService implements Service<GasStation, CreateGasStationRequest, UpdateGasStationRequest> {
    private static final GasStationService INSTANCE = new GasStationService();

    public static GasStationService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<GasStation> getItems(Integer page, Integer size) {
        String getUrl = getEndPoint();
        if(Objects.isNull(page)){
            getUrl += "/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final GasStation[] gasStations = gson.fromJson(stringHttpResponse.body(), GasStation[].class);
            return List.of(gasStations);
        }else {
            getUrl += "?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final GasStationResponseEntity gasStationResponseEntity;
            gasStationResponseEntity = gson.fromJson(stringHttpResponse.body(), GasStationResponseEntity.class);
            if(Objects.nonNull(gasStationResponseEntity._embedded)){
                return gasStationResponseEntity._embedded.gasStationList;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public GasStation addItem(CreateGasStationRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), GasStation.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/gas-station";
    }

    @Override
    public GasStation getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), GasStationResponseEntity.class)._embedded.gasStationList.get(0);
    }

    @Override
    public GasStation delete(Long id) {
        return null;
    }

    @Override
    public GasStation editItem(UpdateGasStationRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final String body = client.parallelPatch(getEndPoint(), payload).body();
        return gson.fromJson(body, GasStation.class);
    }


    private static String getPayload(CreateGasStationRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("name", request.getName());
        payloadObj.put("priceCategoryId", String.valueOf(request.getPriceCategoryId()));
        payloadObj.put("debtLimit", String.valueOf(request.getDebtLimit()));
        payloadObj.put("regionId", request.getRegionId().toString());
        payloadObj.put("ownerId", request.getOwnerId().toString());
        payloadObj.put("groupId", request.getGroupId().toString());
        return new Gson().toJson(payloadObj);
    }
}
