package com.example.model.group;

import com.example.model.Service;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.model.Group;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GroupService implements Service<Group, CreateGroupRequest, UpdateGroupRequest> {
    private static final GroupService INSTANCE = new GroupService();

    public static GroupService getInstance(){
        return INSTANCE;
    }
    @Override
    public List<Group> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=name&value=a&operation=%3E&sort=id,desc";
        }

        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final Group[] groups = gson.fromJson(stringHttpResponse.body(), Group[].class);
        return List.of(groups);
    }

    @Override
    public Group addItem(CreateGroupRequest itemRequest) {
        final String payload = getPayload(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), payload);
        return gson.fromJson(stringHttpResponse.body(), Group.class);
    }

    @Override
    public String getEndPoint() {
        return "api/v1/group";
    }

    @Override
    public Group getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), Group[].class)[0];
    }

    @Override
    public Group editItem(UpdateGroupRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Group.class);
    }

    private static String getPayload(CreateGroupRequest request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("name", request.getName());
        return new Gson().toJson(payloadObj);
    }
}
