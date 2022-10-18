package com.fasid.api.request;

import lombok.Getter;

import lombok.Setter;

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

    public GraphqlDto(Q query, V variables) {
        this.query = query;
        this.variables = variables;
    }

    @Override
    public String toString() {
        return "{"
                + "query=" + query
                + ",variables=" + variables + '\''
                + ",operationName=" + operationName
                + '}';
    }

}
