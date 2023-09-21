package com.example.model.transLine;

import com.example.model.Service;
import com.example.model.transLine.response.TransLineResponseEntity;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateTransLineRequest;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.model.TransLine;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TransLineService implements Service<TransLine, CreateTransLineRequest, UpdateTransLineRequest> {
    private static final TransLineService INSTANCE = new TransLineService();

    public static TransLineService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TransLine> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), TransLine[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final TransLineResponseEntity transLineResponse = gson.fromJson(stringHttpResponse.body(), TransLineResponseEntity.class);
            if(Objects.nonNull(transLineResponse._embedded))
                return transLineResponse._embedded.transLineList;
        }
        return new ArrayList<>();
    }

    @Override
    public TransLine addItem(CreateTransLineRequest itemRequest) {
        final String payload = getPayload(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), TransLine.class);
    }

    @Override
    public String getEndPoint() {
        return "app/v1/trans-line";
    }

    @Override
    public TransLine getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), TransLineResponseEntity.class)._embedded.transLineList.get(0);
    }

    @Override
    public TransLine editItem(UpdateTransLineRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), TransLine.class);
    }

    private static String getPayload(CreateTransLineRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("sourceId", request.getSourceId().toString());
        payloadObj.put("destinationId", String.valueOf(request.getDestinationId()));
        payloadObj.put("feeCurrency", request.getFeeCurrency());
        payloadObj.put("feeAmount", request.getFeeAmount().toString());
        return new Gson().toJson(payloadObj);
    }
}
