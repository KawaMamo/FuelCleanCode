package com.example.model.person;

import com.example.model.client.Client;
import com.example.model.person.response.PersonResponse;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;
import org.example.model.Person;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

public class PersonService {
    Client client = Client.getInstance("http://localhost:8081/");
    private final static PersonService instance = new PersonService();
    Gson gson = AppGson.getGson();

    public List<Person> getPersonList(int page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&test.test=1&sort=id,desc";
        }
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PersonResponse personResponse = gson.fromJson(stringHttpResponse.body(), PersonResponse.class);
        return personResponse._embedded.personList;
    }

    public static PersonService getInstance(){
        return instance;
    }

    public String getEndPoint() {
        return "api/v1/person";
    }
}
