package com.example.model.vehicle;

import com.example.model.client.Client;
import com.example.model.typeAdapter.AppGson;
import com.example.model.vehicle.response.VehicleResponse;
import com.google.gson.*;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.model.Vehicle;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

public class VehicleService {
    private static final VehicleService instance = new VehicleService();
    private final Client client = Client.getInstance("http://localhost:8081/");
    private final Gson gson = AppGson.getGson();

    public static VehicleService getInstance() {
        return instance;
    }

    public List<Vehicle> getVehicles(Integer page){
        String getUrl = "api/v1/vehicle?page="+page+"&size=10&key=plateNumber&value=1&operation=%3E&test.test=1&sort=id,desc";
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final VehicleResponse vehicleResponse = gson.fromJson(stringHttpResponse.body(), VehicleResponse.class);
        return vehicleResponse._embedded.vehicleList;
    }

    public Vehicle addVehicle(CreateVehicleRequest vehicle){
        final String payload = getPayload(vehicle);
        final HttpResponse<String> httpResponse = client.parallelPost("api/v1/vehicle", payload);
        return gson.fromJson(httpResponse.body(), Vehicle.class);
    }

    private static String getPayload(CreateVehicleRequest vehicle) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("plateNumber", vehicle.getPlateNumber());
        payloadObj.put("trafficCenter_id", String.valueOf(vehicle.getTrafficCenter_id()));
        payloadObj.put("driver_id", String.valueOf(vehicle.getDriver_id()));
        payloadObj.put("size", vehicle.getSize().toString());
        payloadObj.put("office_id", vehicle.getOffice_id().toString());
        return new Gson().toJson(payloadObj);
    }
}

