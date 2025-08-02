package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.CarRequest;
import com.bit.partsstore.DTO.CarResponse;
import com.bit.partsstore.models.Car;
import com.bit.partsstore.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cars")
public class CarAdminController {

    private final CarService carService;

    public CarAdminController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarResponse> cars = carService.getCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CarResponse> addCar(@ModelAttribute CarRequest request) {
        CarResponse response = carService.addCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable int id) {
        Car deletedCar = carService.deleteCar(id);
        return ResponseEntity.ok(deletedCar);
    }

}
