package com.example.model.user;

import com.example.model.client.Client;
import com.example.model.mappers.UserMapper;
import com.example.model.mappers.UserMapperImpl;
import com.example.model.user.response.UserResponseEntity;
import com.example.model.user.response.UserResponseList;
import com.google.gson.Gson;
import org.controlsfx.control.Notifications;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

public class UserService {

    private static final UserService instance = new UserService();
    Client client = Client.getInstance("http://localhost:8089/");
    private UserMapper userMapper = new UserMapperImpl();

    private UserService(){}
    public User addUser(User request) {
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("email", request.getEmail());
        payloadObj.put("password", request.getPassword());
        payloadObj.put("role", request.getRole());
        final String payload = new Gson().toJson(payloadObj);
        final HttpResponse<String> post = client.parallelPost("api/v1/register", payload);
        if(post.statusCode() != 200) {
            Notifications.create().text(post.body()).title("Error").showError();
        }else {
            Notifications.create().text("successfully added").title("Success").showInformation();
        }
        final UserResponseList userResponseList = new Gson().fromJson(post.body(), UserResponseList.class);
        return userMapper.toDomain(userResponseList);
    }

    public boolean update(User user, Long id){
        return true;
    }

    public boolean block(User user, boolean lock){
        final HashMap<String, String> payloadObj = new HashMap<>();
        payloadObj.put("email", user.getEmail());
        payloadObj.put("locked", String.valueOf(lock));
        final String payload = new Gson().toJson(payloadObj);
        final HttpResponse<String> post = client.post("api/v1/users/block", payload);
        if(post.statusCode()!= 200) {
            Notifications.create().text(post.body()).title("Error").showError();
        }else {
            Notifications.create().text("successfully blocked").title("Success").showInformation();
        }
        final UserResponseList userResponseList = new Gson().fromJson(post.body(), UserResponseList.class);
        return userResponseList.isLocked();
    }

    public boolean delete(Long id){
        return true;
    }

    public List<User> getUsers(int page, int size, String email, String sortBy, String sortOrder){
        String getUrl = "api/v1/users/listUsers?page="+page+"&size="+size+"&email="+email+"&sort="+sortBy+","+sortOrder+"";
        final HttpResponse<String> stringHttpResponse;
        stringHttpResponse = client.parallelGet(getUrl);
        final UserResponseEntity userResponseEntity = new Gson().fromJson(stringHttpResponse.body(), UserResponseEntity.class);
        return userResponseEntity.get_embedded().getUserResponseList().stream().map(userMapper::toDomain).toList();
    }

    public String checkCredentials(String username, String password){
        return null;
    }

    public static UserService getInstance(){
        return instance;
    }

}
