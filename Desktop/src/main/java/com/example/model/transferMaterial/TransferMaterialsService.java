package com.example.model.transferMaterial;

import com.example.model.Service;
import com.example.model.transferMaterial.response.TransferMaterialsResponseEntity;
import org.example.contract.request.create.CreateTransferMaterialRequest;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.model.TransferMaterials;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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
}
