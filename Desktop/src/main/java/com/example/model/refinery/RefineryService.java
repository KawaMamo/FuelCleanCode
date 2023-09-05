package com.example.model.refinery;

import com.example.model.Service;
import org.example.contract.request.create.CreateRefineryRequest;
import org.example.model.GasStation;
import org.example.model.Refinery;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class RefineryService implements Service<Refinery, CreateRefineryRequest> {
    private static final RefineryService INSTANCE = new RefineryService();
    public static final RefineryService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Refinery> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final Refinery[] refineries = gson.fromJson(stringHttpResponse.body(), Refinery[].class);
        return List.of(refineries);
    }

    @Override
    public Refinery addItem(CreateRefineryRequest itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/refinery";
    }
}
