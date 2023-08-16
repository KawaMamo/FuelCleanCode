package com.example.model;

import lombok.Data;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

@Data
public class Client {
    private static Client instance = new Client();
    private static String JWTToken;
    private static String contentType = "application/json";
    private static String url = "http://localhost:8089/";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private String authorization;

    public HttpResponse<String> post(String endPoint, String payload) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endPoint))
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(payload));
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void addAuthorizationToken(HttpRequest.Builder builder) {
        if(Objects.nonNull(authorization)){
            builder.header("Authorization", authorization);
        }
    }

    public static Client getInstance(){
        return instance;
    }

}
