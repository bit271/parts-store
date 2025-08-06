package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.BrandRequest;
import com.bit.partsstore.DTO.BrandResponse;
import com.bit.partsstore.models.Brand;
import com.bit.partsstore.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/brands")
public class BrandAdminController {

    private final BrandService brandService;

    public BrandAdminController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }

    @PostMapping
    public ResponseEntity<BrandResponse> addBrand(@RequestBody BrandRequest request) {
        BrandResponse response = brandService.addBrand(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Brand> deleteBrand(@PathVariable int id) {
        Brand deletedBrand = brandService.deleteBrand(id);
        return ResponseEntity.ok(deletedBrand);
    }

}
