package com.fasid.api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
public class GraphlqlResponse<T> {

    @Getter
    @Setter
    private Map<String, Map<String, T>> data;

}
