package com.example.model.typeAdapter;

import org.example.model.GasStation;
import org.example.model.Place;
import com.google.gson.*;
import org.example.model.Refinery;

import java.lang.reflect.Type;

public class PlaceTypeAdapter implements JsonSerializer<Place>, JsonDeserializer<Place> {
    @Override
    public Place deserialize(JsonElement jsonElement,
                             Type type,
                             JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Place place;
        final Gson gson = AppGson.getGson();
        try {
            place = gson.fromJson(jsonElement, Refinery.class);
        }catch (Exception e) {
            place = gson.fromJson(jsonElement, GasStation.class);
        }
        return place;
    }

    @Override
    public JsonElement serialize(Place place,
                                 Type type,
                                 JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(place.toString());
    }
}
