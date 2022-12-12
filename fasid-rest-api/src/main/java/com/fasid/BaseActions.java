package com.fasid;

import com.fasid.api.client.RestApiClient;
import com.fasid.api.request.FasidRequestSpecification;
import com.fasid.api.response.FasidResponse;
import com.fasid.enums.MethodType;
import com.fasid.utils.JsonUtils;

/**
 * An Interface for common methods to be extended by all API actions class for common methods
 *
 * @param <T>
 */
public interface BaseActions<T extends BaseActions> {

    default <T> T getApiResponse(MethodType type, FasidRequestSpecification requestSpecification, Class<T> dto) {

        FasidResponse response = new RestApiClient()
                .executeRequest(type, requestSpecification);

        return JsonUtils.jsonToDto(response.getResponseBody(), dto);

    }


}
