package com.example.model;

import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Data
public class Client {
    private static Client instance = new Client();
    private static String JWTToken;
    private static String contentType = "application/json";
    private static String url = "http://localhost:8089/";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public HttpResponse<String> post(String endPoint, String payload) {
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(url+endPoint))
              .header("Content-Type", contentType)
              .POST(HttpRequest.BodyPublishers.ofString(payload))
              .build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Client getInstance(){
        return instance;
    }

}
