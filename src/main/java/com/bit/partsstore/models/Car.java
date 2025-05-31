package com.bit.partsstore.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "date_add")
    private LocalDate dateAdd;

    private String image;

    public Car(Integer id, Model model, Brand brand, String name, Integer year, LocalDate dateAdd, String image) {
        this.id = id;
        this.model = model;
        this.brand = brand;
        this.name = name;
        this.year = year;
        this.dateAdd = dateAdd;
        this.image = image;
    }

    public Car() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
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

    public LocalDate getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(LocalDate dateAdd) {
        this.dateAdd = dateAdd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
