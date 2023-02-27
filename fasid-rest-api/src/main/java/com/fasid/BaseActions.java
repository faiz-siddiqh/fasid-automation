package com.fasid;

import java.util.List;
import java.util.Map;

import com.fasid.api.client.RestApiClient;
import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.api.response.FasidResponse;
import com.fasid.config.Config;
import com.fasid.enums.MethodType;
import com.fasid.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;
import urls.WeatherUrlMapper;

/**
 * An Interface for common methods to be extended by all API actions class for common methods
 *
 * @param <T>
 */
public interface BaseActions<T extends BaseActions> {

    @Deprecated
    default <T> T getApiResponse(MethodType type, FasidRequestSpecification requestSpecification, Class<T> dto) {

        final FasidResponse response = new RestApiClient()
                .executeRequest(type, requestSpecification);

        return JsonUtils.jsonToDto(response.getResponseBody(), dto);

    }

    default <T> T getApiResponse(MethodType type, Class<T> dtoType, Map<String, Object> queryParams) {

        final FasidRequestSpecification requestSpecification = new FasidRequestSpecification()
                .addBaseUrl(Config.getAppUrl() + WeatherUrlMapper.WEATHER.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameter("appid", Config.getToken())
                .addQueryParameters(queryParams)
                .build();

        final FasidResponse response = new RestApiClient()
                .executeRequest(type, requestSpecification);

        return JsonUtils.jsonToDto(response.getResponseBody(), dtoType);

    }

    default <T> List<T> getApiListResponse(MethodType type, Class<T> dtoType, Map<String, Object> queryParams) {

        final FasidRequestSpecification requestSpecification = new FasidRequestSpecification()
                .addBaseUrl(Config.getAppUrl() + WeatherUrlMapper.WEATHER.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameter("appid", Config.getToken())
                .addQueryParameters(queryParams)
                .build();

        final FasidResponse response = new RestApiClient()
                .executeRequest(type, requestSpecification);

        return JsonUtils.convertResponseToPOJOList(response.getResponseBody(), dtoType);

    }

    default String getMessageFromResponse(FasidResponse response) {

        return JsonPath.read(response.getResponseBody(), "$.message");
    }
    
}
