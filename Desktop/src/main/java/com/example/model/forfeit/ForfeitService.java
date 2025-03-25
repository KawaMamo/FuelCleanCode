package com.example.model.forfeit;

import com.example.model.Service;
import com.example.model.forfeit.response.ForfeitResponseEntity;
import org.example.contract.request.create.CreateForfeitRequest;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.model.Forfeit;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ForfeitService implements Service<Forfeit, CreateForfeitRequest, UpdateForfeitRequest> {
    private final static ForfeitService INSTANCE = new ForfeitService();
    public static ForfeitService getInstance() {return INSTANCE;}

    @Override
    public List<Forfeit> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final Forfeit[] forfeits = gson.fromJson(stringHttpResponse.body(),
                    Forfeit[].class);
            return List.of(forfeits);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final ForfeitResponseEntity forfeitResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    ForfeitResponseEntity.class);
            if(Objects.nonNull(forfeitResponseEntity._embedded))
                return forfeitResponseEntity._embedded.forfeitList;
        }
        return new ArrayList<>();
    }

    public List<Forfeit> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final ForfeitResponseEntity forfeitResponseEntity = gson.fromJson(stringHttpResponse.body(),
                ForfeitResponseEntity.class);
        if(Objects.nonNull(forfeitResponseEntity._embedded))
            return forfeitResponseEntity._embedded.forfeitList;
        return new ArrayList<>();
    }

    @Override
    public Forfeit addItem(CreateForfeitRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Forfeit.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/forfeit";
    }

    @Override
    public Forfeit getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), ForfeitResponseEntity.class)._embedded.forfeitList.get(0);
    }

    @Override
    public Forfeit delete(Long id) {
        return gson.fromJson(client.delete(getEndPoint(), "/"+id).body(), Forfeit.class);
    }

    @Override
    public Forfeit editItem(UpdateForfeitRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Forfeit.class);
    }

    public byte[] getForfeitReport(String exportType, LocalDate startDate, LocalDate endDate, Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/forfeitReport" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }
}
