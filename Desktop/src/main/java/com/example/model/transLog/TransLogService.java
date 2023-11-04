package com.example.model.transLog;

import com.example.model.Service;
import com.example.model.transLog.response.TransLogResponseEntity;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.model.TransLog;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransLogService implements Service<TransLog, CreateTransLogRequest, UpdateTransLogRequest> {
    private static final TransLogService INSTANCE = new TransLogService();

    public static TransLogService getInstance(){
        return INSTANCE;
    }
    @Override
    public List<TransLog> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), TransLog[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final TransLogResponseEntity transLogResponseEntity = gson.fromJson(stringHttpResponse.body(), TransLogResponseEntity.class);
            if(Objects.nonNull(transLogResponseEntity._embedded))
                return transLogResponseEntity._embedded.transLogList;
        }
        return new ArrayList<>();
    }

    public List<TransLog> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final TransLogResponseEntity transLogResponseEntity = gson.fromJson(stringHttpResponse.body(), TransLogResponseEntity.class);
        if(Objects.nonNull(transLogResponseEntity._embedded))
            return transLogResponseEntity._embedded.transLogList;

        return new ArrayList<>();
    }

    @Override
    public TransLog addItem(CreateTransLogRequest itemRequest) {
        final String json = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), TransLog.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/trans-log";
    }

    @Override
    public TransLog getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), TransLogResponseEntity.class)._embedded.transLogList.get(0);
    }

    @Override
    public TransLog delete(Long id) {
        return gson.fromJson(client.delete(getEndPoint(), "/"+id).body(), TransLog.class);
    }

    @Override
    public TransLog editItem(UpdateTransLogRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), TransLog.class);
    }
}
