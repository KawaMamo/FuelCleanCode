package com.example.model.partition;

import com.example.model.Service;
import com.example.model.partition.responce.PartitionResponseEntity;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.model.Partition;

import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PartitionService implements Service<Partition, CreatePartitionRequest, UpdatePartitionRequest> {

    private static final PartitionService INSTANCE = new PartitionService();

    public static PartitionService getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public List<Partition> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Partition[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final PartitionResponseEntity partitionResponseEntity = gson.fromJson(stringHttpResponse.body(), PartitionResponseEntity.class);
            if(Objects.nonNull(partitionResponseEntity._embedded))
                return partitionResponseEntity._embedded.partitionList;
        }
        return new ArrayList<>();
    }

    public List<Partition> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+"&"+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PartitionResponseEntity partitionResponseEntity = gson.fromJson(stringHttpResponse.body(), PartitionResponseEntity.class);
        if(Objects.nonNull(partitionResponseEntity._embedded))
            return partitionResponseEntity._embedded.partitionList;

        return new ArrayList<>();
    }

    @Override
    public Partition addItem(CreatePartitionRequest itemRequest) {
        final String json = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Partition.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/partition";
    }

    @Override
    public Partition getItem(Long id) {
        return null;
    }

    @Override
    public Partition delete(Long id) {
        return gson.fromJson(client.delete(getEndPoint(), "/"+id).body(), Partition.class);
    }

    @Override
    public Partition editItem(UpdatePartitionRequest updateRequest) {
        return null;
    }

    public byte[] getReport(String exportType, String transType, LocalDate startDate, LocalDate endDate, Long id){

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/regionResponse64" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate + "/" + transType);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] getPrint(Long id, String username){

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/partitionPrint/" + id + "/" + username);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }

    public byte[] getRefineryReport(String exportType, String transType, LocalDate startDate, LocalDate endDate, Long id){

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getEndPoint()+"/RefineryReport" + "/" + exportType + "/" + id + "/" + startDate + "/" + endDate + "/" + transType);
        return stringHttpResponse.body().getBytes(StandardCharsets.UTF_8);
    }
}
