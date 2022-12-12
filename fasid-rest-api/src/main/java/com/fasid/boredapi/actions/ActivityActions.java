package com.fasid.boredapi.actions;

import com.fasid.BaseActions;
import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.config.Config;
import com.fasid.enums.MethodType;
import io.restassured.http.ContentType;
import urls.ApiUrlMapper;

public class ActivityActions implements BaseActions<ActivityActions> {

    public <T> T createActivitySpec(MethodType type, Class<T> dtoType) {

        FasidRequestSpecification requestSpec = new FasidRequestSpecification()
                .addBaseUrl(Config.getBaseUrl() + ApiUrlMapper.ACTIVITY.getUrl())
                .setContentType(ContentType.JSON)
                .build();

        return getApiResponse(type, requestSpec, dtoType);
    }

    public <T> T getActivitySpec(MethodType type, Class<T> dtoType,String keyValue) {

        FasidRequestSpecification requestSpec = new FasidRequestSpecification()
                .addBaseUrl(Config.getBaseUrl() + ApiUrlMapper.ACTIVITY.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameter("key", keyValue)
                .build();

        return getApiResponse(type, requestSpec, dtoType);
    }

    public <T> T getActivityByTypeSpec(MethodType type, Class<T> dtoType,String activityType) {

        FasidRequestSpecification requestSpec = new FasidRequestSpecification()
                .addBaseUrl(Config.getBaseUrl() + ApiUrlMapper.ACTIVITY.getUrl())
                .setContentType(ContentType.JSON)
                .addQueryParameter("type", activityType)
                .build();

        return getApiResponse(type, requestSpec, dtoType);
    }



}
