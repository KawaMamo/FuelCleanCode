package com.example.model.transferMaterial;

import com.example.model.Service;
import com.example.model.transferMaterial.response.TransferMaterialsResponseEntity;
import org.example.contract.request.create.CreateTransferMaterialRequest;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.model.Money;
import org.example.model.TransferMaterials;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransferMaterialsService implements Service<TransferMaterials, CreateTransferMaterialRequest, UpdateTransferMaterialRequest> {
    private static final TransferMaterialsService INSTANCE = new TransferMaterialsService();

    public static TransferMaterialsService getInstance() {return INSTANCE;}

    @Override
    public List<TransferMaterials> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final TransferMaterials[] buyers = gson.fromJson(stringHttpResponse.body(),
                    TransferMaterials[].class);
            return List.of(buyers);
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final TransferMaterialsResponseEntity buyerResponseEntity = gson.fromJson(stringHttpResponse.body(),
                    TransferMaterialsResponseEntity.class);
            if(Objects.nonNull(buyerResponseEntity._embedded))
                return buyerResponseEntity._embedded.transferMaterialsList;
        }
        return new ArrayList<>();
    }

    public List<TransferMaterials> getItems(Integer page, Integer size, String query){
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TransferMaterialsResponseEntity buyerResponseEntity = gson.fromJson(stringHttpResponse.body(),
                TransferMaterialsResponseEntity.class);
        if(Objects.nonNull(buyerResponseEntity._embedded))
            return buyerResponseEntity._embedded.transferMaterialsList;
        return new ArrayList<>();
    }

    @Override
    public TransferMaterials addItem(CreateTransferMaterialRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), TransferMaterials.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/transfer-materials";
    }

    @Override
    public TransferMaterials getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), TransferMaterialsResponseEntity.class)._embedded.transferMaterialsList.get(0);
    }

    @Override
    public TransferMaterials delete(Long id) {
        return null;
    }

    @Override
    public TransferMaterials editItem(UpdateTransferMaterialRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), TransferMaterials.class);
    }

    public List<Money> getTotalTransfersTo(Long gasStationId) {
        List<Money> list = new ArrayList<Money>();
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint() + "/totalTransfersTo/" + gasStationId);
        for (Object object : gson.fromJson(stringHttpResponse.body(), List.class)) {
            final ArrayList<String> strings = (ArrayList<String>) object;
            list.add(new Money(strings.get(0), Double.valueOf(strings.get(1))));
        }
        return list;
    }

    public List<Money> getTotalTransfersFrom(Long gasStationId) {
        List<Money> list = new ArrayList<Money>();
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint() + "/totalTransfersFrom/" + gasStationId);
        for (Object object : gson.fromJson(stringHttpResponse.body(), List.class)) {
            final ArrayList<String> strings = (ArrayList<String>) object;
            list.add(new Money(strings.get(0), Double.valueOf(strings.get(1))));
        }
        return list;
    }

    public byte[] getTransferReport(String exportType, String transType, LocalDate startDate, LocalDate endDate, Long id){

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/transferReport" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate + "/" + transType);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }
}
