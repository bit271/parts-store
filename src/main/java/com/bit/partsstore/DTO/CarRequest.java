package com.bit.partsstore.DTO;

import org.springframework.web.multipart.MultipartFile;

public class CarRequest {
    private String description;
    private Integer year;
    private Integer brandId;
    private Integer modelId;
    private MultipartFile image;

    public CarRequest() {
    }

    public CarRequest(String description, Integer year, Integer brandId, Integer modelId, MultipartFile image) {
        this.description = description;
        this.year = year;
        this.brandId = brandId;
        this.modelId = modelId;
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}