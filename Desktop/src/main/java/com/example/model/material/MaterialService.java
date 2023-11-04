package com.example.model.material;

import com.example.model.Service;
import com.example.model.material.response.MaterialResponseEntity;
import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.model.Material;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MaterialService implements Service<Material, CreateMaterialRequest, UpdateMaterialRequest> {

    private final static MaterialService INSTANCE = new MaterialService();

    public static MaterialService getInstance() {
        return INSTANCE;
    }
    @Override
    public List<Material> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Material[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final MaterialResponseEntity materialResponse = gson.fromJson(stringHttpResponse.body(), MaterialResponseEntity.class);
            if(Objects.nonNull(materialResponse._embedded))
                return materialResponse._embedded.materialList;
        }
        return new ArrayList<>();
    }

    @Override
    public Material addItem(CreateMaterialRequest itemRequest) {
        final String payload = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Material.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/material";
    }

    @Override
    public Material getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), MaterialResponseEntity.class)._embedded.materialList.get(0);
    }

    @Override
    public Material delete(Long id) {
        return null;
    }

    @Override
    public Material editItem(UpdateMaterialRequest updateRequest) {
        final String payload = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Material.class);
    }
}
