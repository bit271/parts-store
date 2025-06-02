package com.bit.partsstore.DTO;

public class CarResponse {
    private Integer id;
    private String name;
    private Integer year;
    private String image;
    private String brandName;
    private String modelName;

    public CarResponse() {}

    public CarResponse(Integer id, String name, Integer year, String image, String brandName, String modelName) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.image = image;
        this.brandName = brandName;
        this.modelName = modelName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
