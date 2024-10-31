package com.example.model.officePayment;

import com.example.model.Service;
import com.example.model.clientPayment.response.ClientPaymentResponseEntity;
import com.example.model.officePayment.response.OfficePaymentResponseEntity;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.model.ClientPayment;
import org.example.model.OfficePayment;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OfficePaymentService implements Service<OfficePayment, CreateOfficePaymentRequest, UpdateOfficePaymentRequest> {

    private final static OfficePaymentService INSTANCE = new OfficePaymentService();
    public static OfficePaymentService getInstance() {return INSTANCE;}
    @Override
    public List<OfficePayment> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final OfficePayment[] clientPayments = gson.fromJson(stringHttpResponse.body(),
                    OfficePayment[].class);
            return List.of(clientPayments);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final OfficePaymentResponseEntity officePaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    OfficePaymentResponseEntity.class);
            if(Objects.nonNull(officePaymentResponseEntity._embedded))
                return officePaymentResponseEntity._embedded.officePaymentList;
        }
        return new ArrayList<>();
    }

    public List<OfficePayment> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final OfficePaymentResponseEntity officePaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                OfficePaymentResponseEntity.class);
        if(Objects.nonNull(officePaymentResponseEntity._embedded))
            return officePaymentResponseEntity._embedded.officePaymentList;
        return new ArrayList<>();
    }

    @Override
    public OfficePayment addItem(CreateOfficePaymentRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), OfficePayment.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/office-payment";
    }

    @Override
    public OfficePayment getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), OfficePaymentResponseEntity.class)._embedded.officePaymentList.get(0);
    }

    @Override
    public OfficePayment delete(Long id) {
        return null;
    }

    @Override
    public OfficePayment editItem(UpdateOfficePaymentRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), OfficePayment.class);
    }
}
