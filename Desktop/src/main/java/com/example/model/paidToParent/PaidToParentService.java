package com.example.model.paidToParent;

import com.example.model.Service;
import com.example.model.paidToParent.response.PaidToParentResponseEntity;
import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.model.PaidToParent;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaidToParentService implements Service<PaidToParent, CreatePaidToParentRequest, UpdatePaidToParentRequest> {
    private final static PaidToParentService INSTANCE = new PaidToParentService();
    public static PaidToParentService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<PaidToParent> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final PaidToParent[] clientPayments = gson.fromJson(stringHttpResponse.body(),
                    PaidToParent[].class);
            return List.of(clientPayments);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final PaidToParentResponseEntity paidToParentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    PaidToParentResponseEntity.class);
            if(Objects.nonNull(paidToParentResponseEntity._embedded))
                return paidToParentResponseEntity._embedded.paidToParentList;
        }
        return new ArrayList<>();
    }

    public List<PaidToParent> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PaidToParentResponseEntity paidToParentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                PaidToParentResponseEntity.class);
        if(Objects.nonNull(paidToParentResponseEntity._embedded))
            return paidToParentResponseEntity._embedded.paidToParentList;
        return new ArrayList<>();
    }

    @Override
    public PaidToParent addItem(CreatePaidToParentRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), PaidToParent.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/paid-to-parent";
    }

    @Override
    public PaidToParent getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), PaidToParentResponseEntity.class)._embedded.paidToParentList.get(0);
    }

    @Override
    public PaidToParent delete(Long id) {
        return null;
    }

    @Override
    public PaidToParent editItem(UpdatePaidToParentRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), PaidToParent.class);
    }
}
