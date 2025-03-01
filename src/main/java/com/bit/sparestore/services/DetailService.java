package com.bit.sparestore.services;

import com.bit.sparestore.DTO.DetailRequest;
import com.bit.sparestore.DTO.DetailResponse;
import com.bit.sparestore.models.Car;
import com.bit.sparestore.models.Category;
import com.bit.sparestore.models.Detail;
import com.bit.sparestore.repositories.CarRepository;
import com.bit.sparestore.repositories.CategoryRepository;
import com.bit.sparestore.repositories.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {
    private final DetailRepository detailRepository;
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;

    public DetailService(DetailRepository detailRepository, CarRepository carRepository, CategoryRepository categoryRepository) {
        this.detailRepository = detailRepository;
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<DetailResponse> getAllDetails() {
        return detailRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public DetailResponse addDetail(DetailRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Detail detail = new Detail();

        detail.setCar(car);
        detail.setCategory(category);

        //necessarily
        detail.setName(request.getName());
        detail.setAvailableCount(request.getAvailableCount());
        detail.setPrice(request.getPrice());

        //optionally
        detail.setCatalogNum(request.getCatalogNum());
        detail.setDescription(request.getDescription());
        detail.setImage(request.getImage());

        Detail saved = detailRepository.save(detail);
        return mapToResponse(saved);
    }

    private DetailResponse mapToResponse(Detail detail) {
        return new DetailResponse(
                detail.getId(),
                detail.getName(),
                detail.getCatalogNum(),
                detail.getDescription(),
                detail.getImage(),
                detail.getPrice(),
                detail.getAvailableCount(),
                detail.getCar().getName(),         // или getId()
                detail.getCategory().getName()     // или getId()
        );
    }
}
