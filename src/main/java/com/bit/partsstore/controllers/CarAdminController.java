package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.CarRequest;
import com.bit.partsstore.DTO.CarResponse;
import com.bit.partsstore.services.CarService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<CarResponse> addCar(@RequestBody CarRequest request) {
        CarResponse response = carService.addCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
