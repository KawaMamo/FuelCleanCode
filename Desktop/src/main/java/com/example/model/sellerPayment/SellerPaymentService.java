package com.example.model.sellerPayment;

import com.example.model.Service;
import com.example.model.sellerPayment.response.SellerPaymentResponseEntity;
import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.model.SellerPayment;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SellerPaymentService implements Service<SellerPayment, CreateSellerPaymentRequest, UpdateSellerPaymentRequest> {

    private static final SellerPaymentService INSTANCE = new SellerPaymentService();
    public static SellerPaymentService getInstance() {return INSTANCE;}

    @Override
    public List<SellerPayment> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final SellerPayment[] clientPayments = gson.fromJson(stringHttpResponse.body(),
                    SellerPayment[].class);
            return List.of(clientPayments);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final SellerPaymentResponseEntity sellerPaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    SellerPaymentResponseEntity.class);
            if(Objects.nonNull(sellerPaymentResponseEntity._embedded))
                return sellerPaymentResponseEntity._embedded.sellerPaymentList;
        }
        return new ArrayList<>();
    }

    public List<SellerPayment> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final SellerPaymentResponseEntity sellerPaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                SellerPaymentResponseEntity.class);
        if(Objects.nonNull(sellerPaymentResponseEntity._embedded))
            return sellerPaymentResponseEntity._embedded.sellerPaymentList;
        return new ArrayList<>();
    }

    @Override
    public SellerPayment addItem(CreateSellerPaymentRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), SellerPayment.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/seller-payment";
    }

    @Override
    public SellerPayment getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), SellerPaymentResponseEntity.class)._embedded.sellerPaymentList.get(0);
    }

    @Override
    public SellerPayment delete(Long id) {
        return null;
    }

    @Override
    public SellerPayment editItem(UpdateSellerPaymentRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), SellerPayment.class);
    }
}
