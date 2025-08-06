package com.bit.partsstore.services;

import com.bit.partsstore.DTO.PartRequest;
import com.bit.partsstore.DTO.PartResponse;
import com.bit.partsstore.models.Car;
import com.bit.partsstore.models.Category;
import com.bit.partsstore.models.Part;
import com.bit.partsstore.repositories.CarRepository;
import com.bit.partsstore.repositories.CategoryRepository;
import com.bit.partsstore.repositories.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartService {

    private static final String CAR_NOT_FOUND_MSG = "Car not found";
    private static final String CATEGORY_NOT_FOUND_MSG = "Category not found";

    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;

    public PartService(PartRepository partRepository, CarRepository carRepository, CategoryRepository categoryRepository) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PartResponse> getParts() {
        return partRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PartResponse addPart(PartRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException(CAR_NOT_FOUND_MSG));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_MSG));

        Part newPart = createPartFromRequest(request, car, category);
        Part savedPart = partRepository.save(newPart);
        return mapToResponse(savedPart);
    }

    private Part createPartFromRequest(PartRequest partRequest, Car car, Category category) {
        Part part = new Part();
        part.setCar(car);
        part.setCategory(category);
        part.setName(partRequest.getName());
        part.setAvailableCount(partRequest.getAvailableCount());
        part.setPrice(partRequest.getPrice());
        part.setCatalogNum(partRequest.getCatalogNum());
        part.setDescription(partRequest.getDescription());
        part.setImageName(partRequest.getImage());
        return part;
    }

    private PartResponse mapToResponse(Part part) {
        return new PartResponse(
                part.getId(),
                part.getName(),
                part.getCatalogNum(),
                part.getDescription(),
                part.getImageName(),
                part.getPrice(),
                part.getAvailableCount(),
                part.getCar().getDescription(),
                part.getCategory().getName()
        );
    }
}
