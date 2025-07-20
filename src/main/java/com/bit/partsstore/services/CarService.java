package com.bit.partsstore.services;

import com.bit.partsstore.DTO.CarRequest;
import com.bit.partsstore.DTO.CarResponse;
import com.bit.partsstore.models.Brand;
import com.bit.partsstore.models.Car;
import com.bit.partsstore.models.Model;
import com.bit.partsstore.repositories.BrandRepository;
import com.bit.partsstore.repositories.CarRepository;
import com.bit.partsstore.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private static final String BRAND_NOT_FOUND = "Brand not found";
    private static final String MODEL_NOT_FOUND = "Model not found";

    private final CarRepository carRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ImageStorageService imageStorageService;

    public CarService(CarRepository carRepository,
                      BrandRepository brandRepository,
                      ModelRepository modelRepository,
                      ImageStorageService imageStorageService) {
        this.carRepository = carRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.imageStorageService = imageStorageService;
    }

    public List<CarResponse> getCars() {
        return carRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CarResponse addCar(CarRequest request) {
        try {
            Car car = createCarFromRequest(request);
            Car savedCar = carRepository.save(car);
            imageStorageService.saveCarImage(request.getImage(), car.getImageName());
            return mapToResponse(savedCar);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add car", e);
        }
    }

    private Car createCarFromRequest(CarRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException(BRAND_NOT_FOUND));
        Model model = modelRepository.findById(request.getModelId())
                .orElseThrow(() -> new RuntimeException(MODEL_NOT_FOUND));
        String uniqueImageName = UUID.randomUUID() + "_" + request.getImage().getOriginalFilename();

        Car car = new Car();
        car.setDateAdd(LocalDate.now());
        car.setImageName(uniqueImageName);
        car.setName(request.getName());
        car.setYear(request.getYear());
        car.setBrand(brand);
        car.setModel(model);

        return car;
    }

    private CarResponse mapToResponse(Car car) {
        return new CarResponse(
                car.getId(),
                car.getName(),
                car.getYear(),
                car.getImageName(),
                car.getBrand().getName(),
                car.getModel().getName(),
                car.getDateAdd()
        );
    }
}
