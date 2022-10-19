package com.fasid.api.dto.GithubUserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositioryDTO {

    Map<String,String> repoNames;

}
