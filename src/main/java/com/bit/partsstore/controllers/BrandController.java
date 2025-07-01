package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.BrandResponse;
import com.bit.partsstore.services.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public ResponseEntity<List<BrandResponse>> getBrands() {
        List<BrandResponse> brands = brandService.getBrands();
        return ResponseEntity.ok(brands);
    }
}
