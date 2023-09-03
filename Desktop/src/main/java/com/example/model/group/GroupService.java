package com.example.model.group;

import com.example.model.Service;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.model.Group;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class GroupService implements Service<Group, CreateGroupRequest> {
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
        return null;
    }

    @Override
    public String getEndPoint() {
        return "api/v1/group";
    }
}
