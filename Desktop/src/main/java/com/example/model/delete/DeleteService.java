package com.example.model.delete;

import com.example.desktop.vehicles.Vehicles;
import com.example.model.client.Client;

import static com.example.model.properties.AppProperty.getProperties;

public class DeleteService {

    private static DeleteService INSTANCE = new DeleteService();
    private final Client client = Client.getInstance(getProperties().getProperty("fuel.service.host")+":"+ getProperties().getProperty("fuel.service.port") +"/");

    public Integer delete(String url, Long id){
        return client.parallelDelete(url, "/" + id).statusCode();

    }

    public static DeleteService getInstance(){
        return INSTANCE;
    }
}
