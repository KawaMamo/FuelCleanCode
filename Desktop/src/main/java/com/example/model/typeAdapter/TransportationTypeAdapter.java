package com.example.model.typeAdapter;

import com.google.gson.*;
import org.example.model.TransferMaterials;
import org.example.model.Transportation;
import org.example.model.TransportationReason;

import java.lang.reflect.Type;

public class TransportationTypeAdapter implements JsonSerializer<TransportationReason>, JsonDeserializer<TransportationReason> {
    @Override
    public TransportationReason deserialize(JsonElement jsonElement,
                                            Type type,
                                            JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        TransportationReason transportationReason;
        final Gson gson = AppGson.getGson();
        try {
            transportationReason = gson.fromJson(jsonElement, Transportation.class);
        }catch (JsonParseException e) {
            transportationReason = gson.fromJson(jsonElement, TransferMaterials.class);
        }
        return transportationReason;
    }

    @Override
    public JsonElement serialize(TransportationReason transportationReason, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(transportationReason.toString());
    }
}
