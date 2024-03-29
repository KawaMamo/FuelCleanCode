package com.example.model.vehicle;

import com.example.model.client.Client;
import com.example.model.refinery.response.RefineryResponseEntity;
import com.example.model.typeAdapter.AppGson;
import com.example.model.vehicle.response.VehicleResponse;
import com.google.gson.*;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.model.Vehicle;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.model.properties.AppProperty.*;

public class VehicleService {
    private static final VehicleService instance = new VehicleService();
    private final Client client = Client.getInstance(getProperties().getProperty("fuel.service.host")+":"+ getProperties().getProperty("fuel.service.port") +"/");
    private final Gson gson = AppGson.getGson();

    public static VehicleService getInstance() {
        return instance;
    }

    public List<Vehicle> getVehicles(Integer page, Integer size){
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Vehicle[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final VehicleResponse vehicleResponse = gson.fromJson(stringHttpResponse.body(), VehicleResponse.class);
            if(Objects.nonNull(vehicleResponse._embedded))
                return vehicleResponse._embedded.vehicleList;
        }
        return new ArrayList<>();
    }

    public List<Vehicle> getVehicles(Integer page, Integer size, String query){

        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final VehicleResponse vehicleResponse = gson.fromJson(stringHttpResponse.body(), VehicleResponse.class);
        if(Objects.nonNull(vehicleResponse._embedded))
            return vehicleResponse._embedded.vehicleList;
        return new ArrayList<>();
    }

    public Vehicle getVehicle(Long id){
        String getUrl = "api/v1/vehicle?page=0&size=10&key=id&value="+id+"&operation=%3A";
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        return gson.fromJson(stringHttpResponse.body(),VehicleResponse.class)._embedded.vehicleList.get(0);
    }

    public Vehicle addVehicle(CreateVehicleRequest vehicle){
        final String payload = gson.toJson(vehicle);
        final HttpResponse<String> httpResponse = client.parallelPost("api/v1/vehicle", payload);
        return gson.fromJson(httpResponse.body(), Vehicle.class);
    }

    public Vehicle editVehicle(UpdateVehicleRequest vehicle){
        final String payload = gson.toJson(vehicle);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch("api/v1/vehicle", payload);
        return gson.fromJson(stringHttpResponse.body(), Vehicle.class);
    }

    public String getEndPoint() {
        return "api/v1/vehicle";
    }

}

