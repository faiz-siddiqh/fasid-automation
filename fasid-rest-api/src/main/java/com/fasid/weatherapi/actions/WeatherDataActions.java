package com.fasid.weatherapi.actions;

import com.fasid.BaseActions;
import com.fasid.api.client.RestApiClient;
import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.api.response.FasidResponse;
import com.fasid.assertions.Asserts;
import com.fasid.config.Config;
import com.fasid.enums.MethodType;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import urls.WeatherUrlMapper;

import java.util.Map;

public class WeatherDataActions implements BaseActions<WeatherDataActions> {

    /**
     * @param queryParams - QueryParameters To be added for Request Spec
     * @return this
     */
    public WeatherDataActions validateTestFailureOnSendingApiWithoutToken(Map<String, Object> queryParams) {

        FasidRequestSpecification spec = new FasidRequestSpecification()
                .addBaseUrl(Config.getAppUrl() + WeatherUrlMapper.WEATHER.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameters(queryParams)
                .build();

        FasidResponse response = new RestApiClient().executeRequest(MethodType.GET, spec);

        Asserts.assertTrue(response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED, "Error !! Failed Authorization");

        spec
                .addQueryParameter("appid", "adc1e55bfa9af005ccc89d2aa3221507")
                .build();

        response = new RestApiClient().executeRequest(MethodType.GET, spec);

        Asserts.assertTrue(response.getStatusCode() == HttpStatus.SC_UNAUTHORIZED, "Error !! Failed Authorization");
        Asserts.assertTrue(getMessageFromResponse(response).contains("Invalid API key"), "Error !! Message validation for Incorrect appid failed");

        return this;
    }

    public WeatherDataActions validateErrorOnIncorrectQueryParams(Map<String, Object> queryParams) {

        //Removing valid values for cityName and Id . It is imp to remove both as even one correct one will give 200
        Object cityName = queryParams.get("q");
        Object cityId = queryParams.get("id");
        queryParams.remove("q", cityName);
        queryParams.remove("id", "1277333");
        queryParams.put("qa", cityName);


        FasidRequestSpecification requestSpecification = new FasidRequestSpecification()
                .addBaseUrl(Config.getAppUrl() + WeatherUrlMapper.WEATHER.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameters(queryParams)
                .addQueryParameter("appid", Config.getToken())
                .build();

        FasidResponse response = new RestApiClient().executeRequest(MethodType.GET, requestSpecification);

        Asserts.assertTrue(response.getStatusCode() == HttpStatus.SC_BAD_REQUEST, "Error !! Incorrect Query Validation Failed");

        // Checking for incorrect cityId
        requestSpecification
                .addQueryParameter("id", "1221441144444141")
                .build();

        response = new RestApiClient().executeRequest(MethodType.GET, requestSpecification);

        Asserts.assertTrue(response.getStatusCode() == HttpStatus.SC_NOT_FOUND, "Error !! Incorrect Query Validation Failed");
        Asserts.assertTrue(getMessageFromResponse(response).contains("city not found"), "Error !! Error Message validation failed ");
        return this;
    }
}
