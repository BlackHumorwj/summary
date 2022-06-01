package com.kotlin.for2021.view.recyclerView;

import java.io.Serializable;

public class Star implements Serializable {

    private String name;
    private String groundName;

    public Star(String name, String groundName) {
        this.name = name;
        this.groundName = groundName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroundName() {
        return groundName;
    }

    public void setGroundName(String groundName) {
        this.groundName = groundName;
    }
}
