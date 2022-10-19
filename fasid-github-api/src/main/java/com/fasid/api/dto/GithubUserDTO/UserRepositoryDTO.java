package com.fasid.api.dto.GithubUserDTO;

import java.util.ArrayList;

public class UserRepositoryDTO {

    ArrayList<Nodes> nodes;


    public ArrayList<Nodes> getNodes() {
        return this.nodes;
    }

    public void setNodes(ArrayList<Nodes> nodes) {
        this.nodes = nodes;
    }


    public class Nodes {
        public String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}


