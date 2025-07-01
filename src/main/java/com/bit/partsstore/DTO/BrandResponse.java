package com.bit.partsstore.DTO;

public class BrandResponse {
    private Integer id;
    private String name;

    public BrandResponse(Integer id, String name) {
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
