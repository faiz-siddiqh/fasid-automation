package com.fasid.github.dataprovider;

import org.testng.annotations.DataProvider;

public class GithubUserDataprovider {

    @DataProvider(name = "getVariablesForRepositoryDTO")
    public Object[][] getVariablesForRepositoryDTO() {

        int last = 15; //In schema the last represents the number of repositories to fetch
        String variables = "{\n" +
                " \"last\":" + last + "\n" +
                "}";
        return new Object[][]{
                {variables}
        };

    }

}
