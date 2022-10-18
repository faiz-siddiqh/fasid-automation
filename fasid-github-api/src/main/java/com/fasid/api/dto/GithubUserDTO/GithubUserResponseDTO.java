package com.fasid.api.dto.GithubUserDTO;

public class GithubUserResponseDTO {

    private String login;
    public String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
