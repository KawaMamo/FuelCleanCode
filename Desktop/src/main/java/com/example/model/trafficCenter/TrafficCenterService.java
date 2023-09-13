package com.example.model.trafficCenter;

import com.example.model.Service;
import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.contract.request.update.UpdateTrafficCenterRequest;
import org.example.model.TrafficCenter;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class TrafficCenterService implements Service<TrafficCenter, CreateTrafficCenterRequest, UpdateTrafficCenterRequest> {

    private static final TrafficCenterService INSTANCE = new TrafficCenterService();

    @Override
    public List<TrafficCenter> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TrafficCenter[] trafficCenters = gson.fromJson(stringHttpResponse.body(), TrafficCenter[].class);
        return List.of(trafficCenters);
    }

    @Override
    public TrafficCenter addItem(CreateTrafficCenterRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), TrafficCenter.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/traffic-center";
    }

    @Override
    public TrafficCenter getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), TrafficCenter.class);
    }

    @Override
    public TrafficCenter editItem(UpdateTrafficCenterRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), TrafficCenter.class);
    }

    public static TrafficCenterService getInstance(){
        return INSTANCE;
    }

}
