package com.example.model.person;

import com.example.model.client.Client;
import com.example.model.person.response.PersonResponse;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;
import org.example.mappers.PersonDomainMapper;
import org.example.model.Person;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PersonService {
    Client client = Client.getInstance("http://localhost:8081/");
    private final static PersonService instance = new PersonService();
    Gson gson = AppGson.getGson();

    public List<Person> getPersonList(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&test.test=1&sort=id,desc";
        }
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final List<Person> people = Arrays.stream(gson.fromJson(stringHttpResponse.body(), Person[].class)).toList();
        return people;
    }

    public static PersonService getInstance(){
        return instance;
    }

    public String getEndPoint() {
        return "api/v1/person";
    }
}
