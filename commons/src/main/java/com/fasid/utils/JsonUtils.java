package com.fasid.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.testng.util.Strings;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JsonUtils {

    private static GsonBuilder gson;
    private static ObjectMapper objectMapper;
    private static DocumentContext jsonContext;

    private static GsonBuilder getInstance() {
        if (Objects.isNull(gson)) {
            gson = new GsonBuilder()
                    .disableHtmlEscaping()
                    .setPrettyPrinting()
                    .setDateFormat("MM dd, yyyy HH:mm:ss");
        }

        return gson;

    }

    private static ObjectMapper getJacksonInstance() {
        if (Objects.isNull(objectMapper)) {
            objectMapper = new ObjectMapper()
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        return objectMapper;

    }

    public static String toJson(Object dto) {
        return getInstance().create().toJson(dto);
    }

    public static <T> T jsonToDto(Path filePath, Class<T> classs) throws IOException {
        File jsonFile = new File(filePath.toString());
        return getInstance().create().fromJson(new JsonReader(new FileReader(jsonFile)), classs);
    }

    public static <T> T jsonToDto(String json, Class<T> classs) {
        return getInstance().create().fromJson(json, classs);
    }

    public static String jsonToString(Object object) throws JsonProcessingException {

        return getJacksonInstance().writeValueAsString(object);
    }

    public static void writeDataToJson(Map<String, String> map, String filePath, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String source = filePath + File.separator + fileName;
        Map<String, Object> readValue = objectMapper.readValue(Paths.get(source).toFile(), Map.class);
        readValue.putAll(map);
        objectMapper.writeValue(Paths.get(source).toFile(), readValue);

    }

    public static String readValueFromJson(String key, String filePath, String fileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String source = filePath + File.separator + fileName;
        Map<String, Object> map = objectMapper.readValue(Paths.get(source).toFile(), Map.class);

        return map.get(key).toString();
    }

    public static JsonObject readValueFromJsonFile(String filePath, String fileName) throws FileNotFoundException {
        String source = filePath + File.separator + fileName;

        return JsonParser.parseReader(new FileReader(Paths.get(source).toFile())).getAsJsonObject();
    }

    public static List<String> getValueInJsonAsList(String response, String jsonPath) {
        List<String> list = new ArrayList<>();

        JSONArray jsonArray = JsonPath.read(response, jsonPath);

        for (int i = 0; i < jsonArray.size(); i++) {
            list.add(jsonArray.get(i).toString());
        }

        return list;
    }

    public static <T> List<T> convertResponseToPOJOList(String response, Class<T> klass) {
        if (Strings.isNotNullAndNotEmpty(response)) {
            CollectionType referenceType = TypeFactory.defaultInstance().constructCollectionType(List.class, klass);
            try {
                return new ObjectMapper().readValue(response, referenceType);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Please check reference class type and response  ");
            }
        }

        return null;
    }

}
