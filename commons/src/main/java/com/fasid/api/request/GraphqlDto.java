package com.fasid.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class GraphqlDto<Q, V> {

    @Getter
    @Setter
    private Q query;
    @Getter
    @Setter
    private V variables;
    @Getter
    @Setter
    private String operationName;

    @Override
    public String toString() {
        return "{"
                + "query=" + query
                + ",variables=" + variables + '\''
                + ",operationName=" + operationName
                + '}';
    }

}
