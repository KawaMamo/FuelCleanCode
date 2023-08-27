package com.example.model.trafficCenter;

import com.example.model.Service;
import com.example.model.trafficCenter.response.TrafficCenterResponse;
import org.example.contract.request.create.CreateTrafficCenterRequest;
import org.example.model.TrafficCenter;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TrafficCenterService implements Service<TrafficCenter, CreateTrafficCenterRequest> {

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
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/traffic-center";
    }

    public static TrafficCenterService getInstance(){
        return INSTANCE;
    }

}
