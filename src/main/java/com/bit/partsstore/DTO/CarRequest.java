package com.bit.partsstore.DTO;

public class CarRequest {
    private String  name;
    private Integer year;
    private Integer brandId;
    private Integer modelId;
    private String  image;

    public CarRequest() {
    }

    public CarRequest(String name, Integer year, Integer brandId, Integer modelId, String image) {
        this.name = name;
        this.year = year;
        this.brandId = brandId;
        this.modelId = modelId;
        this.image = image;
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

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}