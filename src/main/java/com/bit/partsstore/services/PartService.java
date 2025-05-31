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
    private final PartRepository partRepository;
    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;

    public PartService(PartRepository partRepository, CarRepository carRepository, CategoryRepository categoryRepository) {
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<PartResponse> getAllParts() {
        return partRepository.findAll().stream().map(this::mapToResponse).toList();
    }

    public PartResponse addPart(PartRequest request) {
        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Part part = new Part();

        part.setCar(car);
        part.setCategory(category);

        //necessarily
        part.setName(request.getName());
        part.setAvailableCount(request.getAvailableCount());
        part.setPrice(request.getPrice());

        //optionally
        part.setCatalogNum(request.getCatalogNum());
        part.setDescription(request.getDescription());
        part.setImage(request.getImage());

        Part saved = partRepository.save(part);
        return mapToResponse(saved);
    }

    private PartResponse mapToResponse(Part part) {
        return new PartResponse(
                part.getId(),
                part.getName(),
                part.getCatalogNum(),
                part.getDescription(),
                part.getImage(),
                part.getPrice(),
                part.getAvailableCount(),
                part.getCar().getName(),         // или getId()
                part.getCategory().getName()     // или getId()
        );
    }
}
