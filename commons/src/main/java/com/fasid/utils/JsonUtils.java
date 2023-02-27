package com.fasid.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.testng.util.Strings;

public class JsonUtils {

    private static GsonBuilder gson;
    private static ObjectMapper objectMapper;
    private static DocumentContext jsonContext;

    private JsonUtils() {
        //private constructor to avoid instantiation
    }

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

    public static String toJson(final Object dto) {
        return getInstance().create().toJson(dto);
    }

    public static <T> T jsonToDto(final Path filePath, final Class<T> classs) throws IOException {
        final File jsonFile = new File(filePath.toString());
        return getInstance().create().fromJson(new JsonReader(new FileReader(jsonFile)), classs);
    }

    public static <T> T jsonToDto(final String json, final Class<T> classs) {
        return getInstance().create().fromJson(json, classs);
    }

    public static String jsonToString(final Object object) throws JsonProcessingException {

        return getJacksonInstance().writeValueAsString(object);
    }

    public static void writeDataToJson(final Map<String, String> map, final String filePath, final String fileName) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String source = filePath + File.separator + fileName;
        final Map<String, Object> readValue = objectMapper.readValue(Paths.get(source).toFile(), Map.class);
        readValue.putAll(map);
        objectMapper.writeValue(Paths.get(source).toFile(), readValue);

    }

    public static String readValueFromJson(final String key, final String filePath, final String fileName) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final String source = filePath + File.separator + fileName;
        final Map<String, Object> map = objectMapper.readValue(Paths.get(source).toFile(), Map.class);

        return map.get(key).toString();
    }

    public static JsonObject readValueFromJsonFile(final String filePath, final String fileName) throws FileNotFoundException {
        final String source = filePath + File.separator + fileName;

        return JsonParser.parseReader(new FileReader(Paths.get(source).toFile())).getAsJsonObject();
    }

    public static List<String> getValueInJsonAsList(final String response, final String jsonPath) {
        final List<String> list = new ArrayList<>();

        final JSONArray jsonArray = JsonPath.read(response, jsonPath);

        for (int i = 0; i < jsonArray.size(); i++) {
            list.add(jsonArray.get(i).toString());
        }

        return list;
    }

    public static <T> List<T> convertResponseToPOJOList(final String response, final Class<T> klass) {
        if (Strings.isNotNullAndNotEmpty(response)) {
            final CollectionType referenceType = TypeFactory.defaultInstance().constructCollectionType(List.class, klass);
            try {
                return new ObjectMapper().readValue(response, referenceType);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Please check reference class type and response  ");
            }
        }

        return null;
    }

}
