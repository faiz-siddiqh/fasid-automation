package com.fasid.api.actions;

import com.fasid.api.client.RestApiClient;
import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.api.request.GraphqlDto;
import com.fasid.config.Config;
import com.fasid.enums.MethodType;
import com.fasid.urls.GithubUrlMapper;
import com.fasid.utils.JsonUtils;
import com.jayway.jsonpath.JsonPath;
import io.restassured.http.ContentType;

public class GithubActions {

    private RestApiClient apiClient = new RestApiClient();

    public static String getToken() {
        return "bearer " + Config.getToken();
    }

    public <T> T getApiResponse(final GraphqlDto content, Class<T> type, String path) {
        String response = getGraphQlApiResponse(content);
        Object responseObject = JsonPath.parse(response).read(path);

        return JsonUtils.jsonToDto(JsonUtils.toJson(responseObject), type);
    }

    public String getGraphQlApiResponse(final GraphqlDto content) {

        FasidRequestSpecification requestSpecification = new FasidRequestSpecification()
                .addContentObj(content)
                .setContentType(ContentType.JSON)
                .addBaseUrl(GithubUrlMapper.GRAPHQL_API.getUrlPath())
                .addHeader("Authorization", getToken())
                .build();

        return apiClient.executeRequest(MethodType.POST, requestSpecification).getResponseBody();
    }

}
