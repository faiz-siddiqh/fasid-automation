package com.fasid.api.client;

import java.util.HashMap;
import java.util.Map;

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

public class RestApiClient {

    private boolean debug = true;

    private static final String PROTOCOL_VERSION = "TLSv1.2";

    private boolean followRedirect = true;
    private boolean allowCircularRedirect = true;

    public FasidResponse executeRequest(final MethodType requestType, final FasidRequestSpecification requestSpecification) {

        if (requestSpecification.getContentObj() != null) {
            if (requestSpecification.getContentType().equals(ContentType.JSON)) {
                final String content;
                if (!(requestSpecification.getContentObj() instanceof String)) {
                    content = new Gson().toJson(requestSpecification.getContentObj());
                } else {
                    content = (String) requestSpecification.getContentObj();
                }
                requestSpecification.addContent(content);
            }

        }
        if (!requestSpecification.shouldAllowRedirect()) {
            followRedirect = false;
        }

        if (!requestSpecification.shouldAllowCircularRedirect()) {
            allowCircularRedirect = false;
        }

        return baseRestImpl(requestType, requestSpecification.getBaseUrl(), requestSpecification.getCookies()
                , requestSpecification.getHeaders(), requestSpecification.getQueryParams()
                , requestSpecification.getContentType(), requestSpecification.getContent());
    }

    private final FasidResponse baseRestImpl(final MethodType requestType, final String baseUrl, final Map<String, String> cookies, final Map<String, String> headers,
                                             final Map<String, Object> queryParams, final ContentType contentType, final String content) {

        final RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        final RequestSpecification requestSpecification;

        if (cookies != null && !cookies.isEmpty()) {
            requestSpecBuilder.addCookies(cookies);
        }

        if (headers != null && !headers.isEmpty()) {
            requestSpecBuilder.addHeaders(headers);
        }

        if (queryParams != null && !queryParams.isEmpty()) {
            requestSpecBuilder.addQueryParams(queryParams);
        }

        if (contentType != null) {
            requestSpecBuilder.setContentType(contentType);
        }

        if (content != null) {
            requestSpecBuilder.setBody(content);
        }

        if (debug) {
            requestSpecification = requestSpecBuilder.build().log().all();
        } else {
            requestSpecification = requestSpecBuilder.build();
        }

        final long startTime = System.currentTimeMillis();
        final Response response;
        response = execute(baseUrl, requestType, requestSpecification);
        final long endTime = System.currentTimeMillis();

        final long responseTime = endTime - startTime;

        return processResponse(response, responseTime);
    }

    private Response execute(final String baseUrl, final MethodType requestType, final RequestSpecification requestSpecification) {

        Response response = null;

        final RedirectConfig redirectConfig = new RedirectConfig().followRedirects(followRedirect)
                .allowCircularRedirects(allowCircularRedirect);
        final SSLConfig sslConfig = SSLConfig.sslConfig().relaxedHTTPSValidation(PROTOCOL_VERSION);

        RestAssured.config = RestAssuredConfig.config().sslConfig(sslConfig);

        final RestAssuredConfig config = RestAssured.config()
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

        if (debug) {
            response.prettyPrint();
        }

        return response;
    }

    private FasidResponse processResponse(final Response response, final long responseTime) {
        final FasidResponse fasidResponse = new FasidResponse();

        if (response != null) {
            fasidResponse.setResponseTime(responseTime);

            if (response.getBody() != null) {
                fasidResponse.setResponseBody(response.asPrettyString());
            }

            fasidResponse.setStatusCode(response.getStatusCode());
            fasidResponse.setStatusLine(response.getStatusLine());
            fasidResponse.setCookies(response.getCookies());

            if (response.getHeaders() != null && response.getHeaders().size() > 0) {
                final Map<String, String> responseHeaders = new HashMap<>();
                for (Header header : response.getHeaders()) {
                    responseHeaders.put(header.getName().toLowerCase(), header.getValue());
                }
                fasidResponse.setHeaders(responseHeaders);
            }

        }

        return fasidResponse;
    }

}
