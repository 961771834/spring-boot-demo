package com.leon.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("name")
    private String name;
    @JsonProperty("id")
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
