package com.example.model.client;

import java.net.http.HttpResponse;
import java.util.concurrent.*;

public class ParallelClient {
    private final Callable<HttpResponse<String>> callable;
    public ParallelClient(Callable<HttpResponse<String>> callable) {
        this.callable = callable;
    }

    public HttpResponse<String> call() throws Exception {
        try(final ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            final Future<HttpResponse<String>> future = executorService.submit(callable);
            return future.get();
        }
    }
}
