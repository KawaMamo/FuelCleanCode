package com.example.model.client;

import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.Callable;

import static org.apache.xmlbeans.impl.schema.SchemaTypeLoaderImpl.build;

@Data
public class Client {
    private static Client instance;
    public Client(String url) {
        this.url = url;
    }
    private static String JWTToken;
    private static String contentType = "application/json";
    private String url;
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static String authorization;

    public static void setAuthorization(String authorization) {
        Client.authorization = authorization;
    }
    private ParallelClient parallelClient;

    public HttpResponse<String> post(String endPoint, String payload) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endPoint))
                .header("Content-Type", contentType)
                .POST(HttpRequest.BodyPublishers.ofString(payload));
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {throw new RuntimeException(response.body());}
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> postMultiPart(String endPoint, String payload, String fileUrl) throws IOException, InterruptedException {

        final HttpEntity multipartEntityBuilder = MultipartEntityBuilder
                .create()
                .addBinaryBody("document", new File(fileUrl))
                .addPart("request", new StringBody(payload, ContentType.MULTIPART_FORM_DATA))
                .build();
        Pipe pipe = Pipe.open();
        new Thread(() -> {
            try (OutputStream outputStream = Channels.newOutputStream(pipe.sink())) {
                multipartEntityBuilder.writeTo(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endPoint))
                .header("Content-Type", multipartEntityBuilder.getContentType().getValue())
                .POST(HttpRequest.BodyPublishers.ofInputStream(
                        () -> Channels.newInputStream(pipe.source())));
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if(response.statusCode() != 200) {
            throw new RuntimeException(response.body());
        }
        return response;
    }

    public HttpResponse<String> patch(String endPoint, String payload) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endPoint))
                .header("Content-Type", contentType)
                .method("PATCH", HttpRequest.BodyPublishers.ofString(payload));
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new RuntimeException(response.body());
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> delete(String endPoint, String payload) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(url + endPoint+payload))
                .header("Content-Type", contentType)
                .DELETE();
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200){
                throw new RuntimeException(response.body());
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> parallelPost(String endPoint, String payload){
        Callable<HttpResponse<String>> callable = new Callable<HttpResponse<String>>() {
            @Override
            public HttpResponse<String> call() throws Exception {
                return post(endPoint, payload);
            }
        };
        parallelClient = new ParallelClient(callable);
        try {
            return parallelClient.call();
        } catch (Exception e) {
            Notifications.create().text("Something went wrong").showError();
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> parallelPatch(String endPoint, String payload){
        Callable<HttpResponse<String>> callable = new Callable<HttpResponse<String>>() {
            @Override
            public HttpResponse<String> call() throws Exception {
                return patch(endPoint, payload);
            }
        };
        parallelClient = new ParallelClient(callable);
        try {
            return parallelClient.call();
        } catch (Exception e) {
            Notifications.create().text("Something went wrong").showError();
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> parallelDelete(String endPoint, String payload){
        Callable<HttpResponse<String>> callable = new Callable<HttpResponse<String>>() {
            @Override
            public HttpResponse<String> call() throws Exception {
                return delete(endPoint, payload);
            }
        };
        parallelClient = new ParallelClient(callable);
        try {
            return parallelClient.call();
        } catch (Exception e) {
            Notifications.create().text("Something went wrong").showError();
            throw new RuntimeException(e);
        }
    }

    private void addAuthorizationToken(HttpRequest.Builder builder) {
        if(Objects.nonNull(authorization)){
            builder.header("Authorization", authorization);
        }
    }

    public HttpResponse<String> get(String endPoint) {
        final HttpRequest.Builder builder = HttpRequest.newBuilder()
               .uri(URI.create(url + endPoint))
               .header("Content-Type", contentType)
                .GET();
        addAuthorizationToken(builder);
        final HttpRequest request = builder.build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new RuntimeException(response.body());
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpResponse<String> parallelGet(String endPoint){
        Callable<HttpResponse<String>> callable = new Callable<HttpResponse<String>>() {
            @Override
            public HttpResponse<String> call() throws Exception {
                return get(endPoint);
            }
        };
        parallelClient = new ParallelClient(callable);
        try {
            return parallelClient.call();
        } catch (Exception e) {
            Notifications.create().text("Something went wrong").showError();
            throw new RuntimeException(e);
        }
    }

    public static Client getInstance(String url){

        if(Objects.isNull(instance)){
            instance = new Client(url);
        }else if(!instance.getUrl().equals(url)){
            instance = new Client(url);
        }

        return instance;
    }

}
