package com.example.model.delete;

import com.example.model.client.Client;

public class DeleteService {

    private static DeleteService INSTANCE = new DeleteService();
    private final Client client = Client.getInstance("http://localhost:8081/");

    public Integer delete(String url, Long id){
        return client.parallelDelete(url, "/" + id).statusCode();

    }

    public static DeleteService getInstance(){
        return INSTANCE;
    }
}
