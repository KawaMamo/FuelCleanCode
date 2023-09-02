package com.example.model.transLine;

import com.example.model.Service;
import org.example.model.TransLine;
import org.example.useCases.create.CreateTransLine;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class TransLineService implements Service<TransLine, CreateTransLine> {
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
    public TransLine addItem(CreateTransLine itemRequest) {
        return null;
    }

    @Override
    public String getEndPoint() {
        return "app/v1/trans-line";
    }
}
