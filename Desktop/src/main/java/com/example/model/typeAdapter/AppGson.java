package com.example.model.typeAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Place;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class AppGson {
    private static Gson gson;

    public static Gson getGson() {
        if(Objects.isNull(gson)){
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .registerTypeAdapter(Place.class, new PlaceTypeAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return gson;
    }

}
