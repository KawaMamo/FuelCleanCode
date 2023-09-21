package com.example.model.refinery;

import com.example.model.Service;
import com.example.model.refinery.response.RefineryResponseEntity;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.contract.request.update.UpdateRefineryRequest;
import org.example.model.GasStation;
import org.example.model.Refinery;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RefineryService implements Service<Refinery, CreateRefineryRequest, UpdateRefineryRequest> {
    private static final RefineryService INSTANCE = new RefineryService();
    public static final RefineryService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Refinery> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Refinery[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final RefineryResponseEntity refineryResponseEntity = gson.fromJson(stringHttpResponse.body(), RefineryResponseEntity.class);
            if(Objects.nonNull(refineryResponseEntity._embedded))
                return refineryResponseEntity._embedded.refineryList;
        }
        return new ArrayList<>();
    }

    @Override
    public Refinery addItem(CreateRefineryRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Refinery.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/refinery";
    }

    @Override
    public Refinery getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), RefineryResponseEntity.class)._embedded.refineryList.get(0);
    }

    @Override
    public Refinery editItem(UpdateRefineryRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Refinery.class);
    }
}
