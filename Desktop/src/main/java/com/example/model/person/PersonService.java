package com.example.model.person;

import com.example.model.Service;
import com.example.model.client.Client;
import com.example.model.person.response.PersonResponse;
import com.example.model.typeAdapter.AppGson;
import com.google.gson.Gson;
import org.example.contract.request.create.CreatePersonRequest;
import org.example.contract.request.update.UpdatePersonRequest;
import org.example.mappers.PersonDomainMapper;
import org.example.model.Person;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class PersonService implements Service<Person, CreatePersonRequest, UpdatePersonRequest> {

    private final static PersonService instance = new PersonService();

    @Override
    public List<Person> getItems(Integer page, Integer size) {
        String getUrl;
        if(Objects.isNull(page)){
            getUrl = getEndPoint()+"/all";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            return List.of(gson.fromJson(stringHttpResponse.body(), Person[].class));
        }else {
            getUrl = getEndPoint()+"?page="+page+"&size="+size+"&key=id&value=0&operation=%3E&test.test=1&sort=id,desc";
            final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
            final PersonResponse personResponse = gson.fromJson(stringHttpResponse.body(), PersonResponse.class);
            if(Objects.nonNull(personResponse._embedded))
                return personResponse._embedded.personList;
        }
        return new ArrayList<>();
    }

    public List<Person> getItems(Integer page, Integer size, String query) {
        String getUrl = getEndPoint()+"?page="+page+"&size="+size+query;
        final HttpResponse<String> stringHttpResponse = client.parallelGet(getUrl);
        final PersonResponse personResponse = gson.fromJson(stringHttpResponse.body(), PersonResponse.class);
        if(Objects.nonNull(personResponse._embedded))
            return personResponse._embedded.personList;
        return new ArrayList<>();
    }

    public static PersonService getInstance(){
        return instance;
    }

    @Override
    public Person addItem(CreatePersonRequest itemRequest) {
        final String toJson = gson.toJson(itemRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPost(getEndPoint(), toJson);
        return gson.fromJson(stringHttpResponse.body(),Person.class);
    }

    public String getEndPoint() {
        return "api/v1/person";
    }

    @Override
    public Person getItem(Long id) {
        return gson.fromJson(getResponse(id).body(), PersonResponse.class)._embedded.personList.get(0);
    }

    @Override
    public Person delete(Long id) {
        return null;
    }

    @Override
    public Person editItem(UpdatePersonRequest updateRequest) {
        final String json = gson.toJson(updateRequest);
        final HttpResponse<String> stringHttpResponse = client.parallelPatch(getEndPoint(), json);
        return gson.fromJson(stringHttpResponse.body(), Person.class);
    }
}
