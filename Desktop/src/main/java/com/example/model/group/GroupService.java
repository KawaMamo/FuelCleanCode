package com.example.model.group;

import com.example.model.Service;
import com.example.model.group.response.GroupResponseEntity;
import com.google.gson.Gson;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.model.Group;

import java.net.http.HttpResponse;
import java.util.ArrayList;
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
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Group[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final GroupResponseEntity groupResponse = gson.fromJson(stringHttpResponse.body(), GroupResponseEntity.class);
            if (Objects.nonNull(groupResponse._embedded))
                return groupResponse._embedded.groupList;
        }
        return new ArrayList<>();
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
        return gson.fromJson(getResponse(id).body(), GroupResponseEntity.class)._embedded.groupList.get(0);
    }

    @Override
    public Group delete(Long id) {
        return null;
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
