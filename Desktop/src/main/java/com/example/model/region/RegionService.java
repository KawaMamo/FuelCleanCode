package com.example.model.region;

import com.example.model.Service;
import org.example.contract.request.create.CreateRegionRequest;
import org.example.model.Region;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class RegionService implements Service<Region, CreateRegionRequest> {
    private static final RegionService INSTANCE = new RegionService();
    public static RegionService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Region> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final Region[] regions = gson.fromJson(stringHttpResponse.body(), Region[].class);
        return List.of(regions);
    }

    @Override
    public Region addItem(CreateRegionRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/region";
    }
}
