package com.example.model.returnedMaterial;

import com.example.model.Service;
import com.example.model.returnedMaterial.response.ReturnedMaterialResponseEntity;
import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.contract.request.update.UpdateReturnedMaterialRequest;
import org.example.model.Money;
import org.example.model.ReturnedMaterial;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReturnedMaterialService implements Service<ReturnedMaterial, CreateReturnedMaterialRequest, UpdateReturnedMaterialRequest> {

    private static final ReturnedMaterialService INSTANCE = new ReturnedMaterialService();
    public static ReturnedMaterialService getInstance() {return INSTANCE;}
    @Override
    public List<ReturnedMaterial> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)) {
            getUrl = getEndPoint() + "/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), ReturnedMaterial[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final ReturnedMaterialResponseEntity responseEntity = gson.fromJson(stringHttpResponse.body(), ReturnedMaterialResponseEntity.class);
            if(Objects.nonNull(responseEntity._embedded))
                return responseEntity._embedded.returnedMaterialList;
        }
        return null;
    }

    public List<ReturnedMaterial> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final ReturnedMaterialResponseEntity responseEntity = gson.fromJson(stringHttpResponse.body(), ReturnedMaterialResponseEntity.class);
        if(Objects.nonNull(responseEntity._embedded))
            return responseEntity._embedded.returnedMaterialList;
        return new ArrayList<>();
    }

    @Override
    public ReturnedMaterial addItem(CreateReturnedMaterialRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), ReturnedMaterial.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/returned-material";
    }

    @Override
    public ReturnedMaterial getItem(Long id) {
        return gson.fromJson(getResponse(id).body(),ReturnedMaterialResponseEntity.class)._embedded.returnedMaterialList.get(0);
    }

    @Override
    public ReturnedMaterial delete(Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelDelete(getEndPoint(), "/" + id);
        return gson.fromJson(stringHttpResponse.body(), ReturnedMaterial.class);
    }

    @Override
    public ReturnedMaterial editItem(UpdateReturnedMaterialRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), ReturnedMaterial.class);
    }

    public List<Money> getTotalReturnedMaterials(Long gasStationId) {
        List<Money> list = new ArrayList<Money>();
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint() + "/totalReturnedMaterials/" + gasStationId);
        for (Object object : gson.fromJson(stringHttpResponse.body(), List.class)) {
            final ArrayList<String> strings = (ArrayList<String>) object;
            list.add(new Money(strings.get(0), Double.valueOf(strings.get(1))));
        }
        return list;
    }

    public byte[] getReturnedMaterialsReport(String exportType, LocalDate startDate, LocalDate endDate, Long id) {
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/returnedMaterialsReport" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }
}
