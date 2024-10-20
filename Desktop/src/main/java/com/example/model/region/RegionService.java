package com.example.model.region;

import com.example.model.Service;
import com.example.model.region.response.RegionResponseEntity;
import org.example.contract.request.create.CreateRegionRequest;
import org.example.contract.request.update.UpdateRegionRequest;
import org.example.model.Region;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegionService implements Service<Region, CreateRegionRequest, UpdateRegionRequest> {
    private static final RegionService INSTANCE = new RegionService();
    public static RegionService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Region> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Region[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final RegionResponseEntity regionResponse = gson.fromJson(stringHttpResponse.body(), RegionResponseEntity.class);
            if(Objects.nonNull(regionResponse._embedded))
                return regionResponse._embedded.regionList;
        }
        return new ArrayList<>();
    }

    public List<Region> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final RegionResponseEntity regionResponse = gson.fromJson(stringHttpResponse.body(), RegionResponseEntity.class);
        if(Objects.nonNull(regionResponse._embedded))
            return regionResponse._embedded.regionList;
        return new ArrayList<>();
    }

    @Override
    public Region addItem(CreateRegionRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Region.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/region";
    }

    @Override
    public Region getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), RegionResponseEntity.class)._embedded.regionList.get(0);
    }

    @Override
    public Region delete(Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelDelete(getEndPoint(), "/" + id);
        return gson.fromJson(stringHttpResponse.body(), Region.class);
    }

    @Override
    public Region editItem(UpdateRegionRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Region.class);
    }
}
