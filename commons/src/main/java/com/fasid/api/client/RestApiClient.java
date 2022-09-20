package com.fasid.api.client;

import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.api.response.FasidResponse;
import com.fasid.enums.MethodType;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class RestApiClient {

    private boolean debug = true;

    private static final String PROTOCOL_VERSION = "TLSv1.2";

    private boolean followRedirect = true;
    private boolean allowCircularRedirect = true;

    public FasidResponse executeRequest(MethodType requestType, FasidRequestSpecification requestSpecification) {


        if (requestSpecification.getContentObj() != null) {
            if (requestSpecification.getContentType().equals(ContentType.JSON)) {
                String content;
                if (!(requestSpecification.getContentObj() instanceof String)) {
                    content = new Gson().toJson(requestSpecification.getContentObj());
                } else {
                    content = (String) requestSpecification.getContentObj();
                }
                requestSpecification.addContentObj(content);
            }

        }
        if (!requestSpecification.shouldAllowRedirect())
            followRedirect = false;

        if (!requestSpecification.shouldAllowCircularRedirect())
            allowCircularRedirect = false;


        return baseRestImpl(requestType, requestSpecification.getBaseUrl(), requestSpecification.getCookies()
                , requestSpecification.getHeaders(), requestSpecification.getQueryParams()
                , requestSpecification.getContentType(), requestSpecification.getContent());
    }

  
    private final FasidResponse baseRestImpl(MethodType requestType, String baseUrl, Map<String, String> cookies, Map<String, String> headers,
                                             Map<String, String> queryParams, ContentType contentType, String content) {

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        RequestSpecification requestSpecification;

        if (cookies != null && !cookies.isEmpty())
            requestSpecBuilder.addCookies(cookies);

        if (headers != null && !headers.isEmpty())
            requestSpecBuilder.addHeaders(headers);

        if (queryParams != null && !queryParams.isEmpty())
            requestSpecBuilder.addQueryParams(queryParams);

        if (contentType != null)
            requestSpecBuilder.setContentType(contentType);

        if (content != null)
            requestSpecBuilder.setBody(content);

        if (debug)
            requestSpecification = requestSpecBuilder.build().log().all();
        else
            requestSpecification = requestSpecBuilder.build();

        long startTime = System.currentTimeMillis();
        Response response;
        response = execute(baseUrl, requestType, requestSpecification);
        long endTime = System.currentTimeMillis();

        long responseTime = endTime - startTime;


        return processResponse(response, responseTime);
    }


    private Response execute(String baseUrl, MethodType requestType, RequestSpecification requestSpecification) {

        Response response = null;

        RedirectConfig redirectConfig = new RedirectConfig().followRedirects(followRedirect)
                .allowCircularRedirects(allowCircularRedirect);
        SSLConfig sslConfig = SSLConfig.sslConfig().relaxedHTTPSValidation(PROTOCOL_VERSION);

        RestAssured.config = RestAssuredConfig.config().sslConfig(sslConfig);

        RestAssuredConfig config = RestAssured.config()
                .redirect(redirectConfig)
                .sslConfig(sslConfig);

        try {
            switch (requestType) {
                case GET:
                    response = RestAssured.given().config(config).spec(requestSpecification).get(baseUrl);
                    break;
                case POST:
                    response = RestAssured.given().config(config).spec(requestSpecification).post(baseUrl);
                    break;
                case PUT:
                    response = RestAssured.given().config(config).spec(requestSpecification).put(baseUrl);
                    break;
                case DELETE:
                    response = RestAssured.given().config(config).spec(requestSpecification).delete(baseUrl);
                    break;
                default:
                    throw new RuntimeException("Invalid !!Please check RequestType -" + requestType);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (debug)
            response.prettyPrint();


        return response;
    }


    private FasidResponse processResponse(Response response, long responseTime) {
        FasidResponse fasidResponse = new FasidResponse();

        if (response != null) {
            fasidResponse.setResponseTime(responseTime);

            if (response.body() != null)
                fasidResponse.setResponseBody(response.getBody().toString());

            fasidResponse.setStatusCode(response.getStatusCode());
            fasidResponse.setStatusLine(response.getStatusLine());
            fasidResponse.setCookies(response.getCookies());

            if (response.getHeaders() != null && response.getHeaders().size() > 0) {
                Map<String, String> responseHeaders = new HashMap<>();
                for (Header header : response.getHeaders()) {
                    responseHeaders.put(header.getName().toLowerCase(), header.getValue());
                }
                fasidResponse.setHeaders(responseHeaders);
            }

        }

        return fasidResponse;
    }

}
