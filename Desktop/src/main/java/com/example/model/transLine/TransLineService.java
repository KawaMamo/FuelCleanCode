package com.example.model.transLine;

import com.example.model.Service;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateTransLineRequest;
import org.example.model.TransLine;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class TransLineService implements Service<TransLine, CreateTransLineRequest> {
    private static final TransLineService INSTANCE = new TransLineService();

    public static TransLineService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<TransLine> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=source&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TransLine[] transLines = gson.fromJson(stringHttpResponse.body(), TransLine[].class);
        return List.of(transLines);
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

    private static String getPayload(CreateTransLineRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("sourceId", request.getSourceId().toString());
        payloadObj.put("destinationId", String.valueOf(request.getDestinationId()));
        payloadObj.put("feeCurrency", request.getFeeCurrency());
        payloadObj.put("feeAmount", request.getFeeAmount().toString());
        return new Gson().toJson(payloadObj);
    }
}
