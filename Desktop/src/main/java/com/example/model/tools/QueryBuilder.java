package com.example.model.tools;

import lombok.Data;

import java.util.Objects;

@Data
public class QueryBuilder {
    private String query = "";

    public void addQueryParameter(String key, String value, String operation){
        if(Objects.nonNull(value))
            query = query.concat("&key="+key+"&value="+value+"&operation="+operation);
    }

    public void addQueryParameter(String key, String value){
        if(Objects.nonNull(value))
            query = query.concat("&key="+key+"&value="+value+"&operation="+"%3A");
    }

    public void sort(String column, String order){
        query = query.concat("&sort="+column+","+order+"");
    }

}
