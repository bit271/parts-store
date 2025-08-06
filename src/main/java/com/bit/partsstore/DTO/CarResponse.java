package com.bit.partsstore.DTO;

import java.time.LocalDate;

public class CarResponse {
    private Integer id;
    private String description;
    private Integer year;
    private String imageName;
    private String brandName;
    private String modelName;
    private LocalDate dateAdd;

    public CarResponse(Integer id, String description, Integer year, String imageName, String brandName, String modelName, LocalDate dateAdd) {
        this.id = id;
        this.description = description;
        this.year = year;
        this.imageName = imageName;
        this.brandName = brandName;
        this.modelName = modelName;
        this.dateAdd = dateAdd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public LocalDate getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(LocalDate dateAdd) {
        this.dateAdd = dateAdd;
    }
    
}
