package com.bit.partsstore.DTO;

public class ModelResponse {
    private Integer id;
    private String name;

    public ModelResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
