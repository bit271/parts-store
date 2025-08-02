package com.bit.partsstore.services;

import com.bit.partsstore.DTO.BrandRequest;
import com.bit.partsstore.DTO.BrandResponse;
import com.bit.partsstore.models.Brand;
import com.bit.partsstore.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private static final String BRAND_ALREADY_EXISTS = "Brand already exists";
    private static final String BRAND_DOESNT_FOUND = "Brand doesn't found";
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandResponse> getBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BrandResponse addBrand(BrandRequest request) {
        if (brandRepository.existsByName(request.getName())) {
            throw new RuntimeException(BRAND_ALREADY_EXISTS);
        }
        Brand brand = createBrandFromRequest(request);
        Brand saved = brandRepository.save(brand);
        return mapToResponse(saved);
    }

    public Brand deleteBrand(int id) {
        return brandRepository.findById(id)
                .map(brand -> {
                    brandRepository.delete(brand);
                    return brand;
                })
                .orElseThrow(() -> new RuntimeException(BRAND_DOESNT_FOUND));
    }

    private Brand createBrandFromRequest(BrandRequest request) {
        Brand brand = new Brand();
        brand.setName(request.getName());
        return brand;
    }

    private BrandResponse mapToResponse(Brand brand) {
        return new BrandResponse(brand.getId(), brand.getName());
    }
}
