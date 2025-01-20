package com.example.model.clientPayment;

import com.example.model.Service;
import com.example.model.clientPayment.response.ClientPaymentResponseEntity;
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.model.ClientPayment;
import org.example.model.Money;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClientPaymentService implements Service<ClientPayment, CreateClientPaymentRequest, UpdateClientPaymentRequest> {
    private final static ClientPaymentService INSTANCE = new ClientPaymentService();
    public static ClientPaymentService getInstance() {return INSTANCE;}

    @Override
    public List<ClientPayment> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final ClientPayment[] clientPayments = gson.fromJson(stringHttpResponse.body(),
                    ClientPayment[].class);
            return List.of(clientPayments);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final ClientPaymentResponseEntity clientPaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    ClientPaymentResponseEntity.class);
            if(Objects.nonNull(clientPaymentResponseEntity._embedded))
                return clientPaymentResponseEntity._embedded.clientPaymentList;
        }
        return new ArrayList<>();
    }

    public List<ClientPayment> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final ClientPaymentResponseEntity clientPaymentResponseEntity = gson.fromJson(stringHttpResponse.body(),
                ClientPaymentResponseEntity.class);
        if(Objects.nonNull(clientPaymentResponseEntity._embedded))
            return clientPaymentResponseEntity._embedded.clientPaymentList;
        return new ArrayList<>();
    }

    @Override
    public ClientPayment addItem(CreateClientPaymentRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), ClientPayment.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/client-payment";
    }

    @Override
    public ClientPayment getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), ClientPaymentResponseEntity.class)._embedded.clientPaymentList.get(0);
    }

    @Override
    public ClientPayment delete(Long id) {
        return null;
    }

    @Override
    public ClientPayment editItem(UpdateClientPaymentRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), ClientPayment.class);
    }

    public List<Money> getTotalPaymentsForClient(Long clientId) {
        List<Money> list = new ArrayList<Money>();
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint() + "/totalPayments/" + clientId);
        for (Object object : gson.fromJson(stringHttpResponse.body(), List.class)) {
            final ArrayList<String> strings = (ArrayList<String>) object;
            list.add(new Money(strings.get(0), Double.valueOf(strings.get(1))));
        }
        return list;
    }

    public byte[] getPaymentsReport(String exportType, LocalDate startDate, LocalDate endDate, Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/clientsPayments" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }
}
