package com.example.model.typeAdapter;

import com.example.model.transportation.response.TransportationResponseEntity;
import com.google.gson.*;
import org.apache.xmlbeans.impl.piccolo.xml.DocumentEntity;
import org.example.model.Document;
import org.example.model.Place;
import org.example.model.TransportationReason;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;

public class AppGson {
    private static Gson gson;

    public static Gson getGson() {
        if(Objects.isNull(gson)){
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                    .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                    .registerTypeAdapter(Place.class, new PlaceTypeAdapter())
                    .registerTypeAdapter(TransportationReason.class, new TransportationTypeAdapter())
                    .registerTypeHierarchyAdapter(Document.class, new ByteArrayToBase64TypeAdapter())
                    .setPrettyPrinting()
                    .create();
        }
        return gson;
    }



}
 class ByteArrayToBase64TypeAdapter implements JsonSerializer<Document>, JsonDeserializer<Document> {


     @Override
     public Document deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         final Document document = new Document();
         document.setContent(jsonElement.getAsJsonObject().get("content").toString().getBytes(StandardCharsets.UTF_8));
         document.setType(jsonElement.getAsJsonObject().get("type").toString());
         document.setUrl(jsonElement.getAsJsonObject().get("url").toString());
         return document;
     }

     @Override
     public JsonElement serialize(Document document, Type type, JsonSerializationContext jsonSerializationContext) {
         return new JsonPrimitive(Base64.getEncoder().encodeToString(document.getContent()));
     }
 }
