package com.jskno.bingapitrackerbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BingProvider {

    @JsonProperty("_type")
    private String type;

    private String name;

    public BingProvider() {
    }

    public BingProvider(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
