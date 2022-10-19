package com.fasid.github.tests.viewer;

import com.fasid.api.actions.GithubActions;
import com.fasid.api.dto.GithubUserDTO.GithubUserResponseDTO;
import com.fasid.api.dto.GithubUserDTO.UserRepositoryDTO;
import com.fasid.api.request.GraphqlDto;
import com.fasid.assertions.Asserts;
import com.fasid.github.dataprovider.GithubUserDataprovider;
import com.fasid.github.tests.BaseApiTest;
import com.fasid.utils.FileUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubUserTests extends BaseApiTest {

    @Test(description = "Validate Current User Info")
    public void validateUserInfo() throws IOException {

        String expectedName = "Faiz Ahmed Siddiqh K";
        final String schema = FileUtils.readFromAFile(getSchema("QueryMeSchema"));
        GithubUserResponseDTO responseDTO = new GithubActions()
                .getApiResponse(new GraphqlDto<>(schema, ""), GithubUserResponseDTO.class, "$.data.viewer");
        Asserts.assertTrue(responseDTO.getName().equalsIgnoreCase(expectedName),
                "Error !! Github username -expected =" + expectedName + ",actual Found =" + responseDTO.getName());

    }

    @Test(description = "Validate All the repositories of the user"
            , dataProvider = "getVariablesForRepositoryDTO"
            , dataProviderClass = GithubUserDataprovider.class)
    public void validateRepositoriesOfUser(String variables) throws IOException {

        final String schema = FileUtils.readFromAFile(getSchema("GetRepositorySchema"));
        UserRepositoryDTO responseDTO = new GithubActions()
                .getApiResponse(new GraphqlDto<>(schema, variables), UserRepositoryDTO.class, "$.data.viewer.repositories");

        responseDTO.getNodes().forEach(node -> System.out.println(node.getName()));
    }

}
