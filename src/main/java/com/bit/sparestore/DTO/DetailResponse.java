package com.bit.sparestore.DTO;

public class DetailResponse {
    private Integer id;
    private String  name;
    private String  catalogNum;
    private String  description;
    private String  image;
    private Integer price;
    private Integer availableCount;
    private String  carName;
    private String  categoryName;

    public DetailResponse() {}

    public DetailResponse(Integer id, String name, String catalogNum, String description, String image, Integer price, Integer availableCount, String carName, String categoryName) {
        this.id = id;
        this.name = name;
        this.catalogNum = catalogNum;
        this.description = description;
        this.image = image;
        this.price = price;
        this.availableCount = availableCount;
        this.carName = carName;
        this.categoryName = categoryName;
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

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(Integer availableCount) {
        this.availableCount = availableCount;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
