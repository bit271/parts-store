package com.bit.sparestore.controllers;

import com.bit.sparestore.DTO.DetailRequest;
import com.bit.sparestore.DTO.DetailResponse;
import com.bit.sparestore.services.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/details")
public class DetailController {

    private final DetailService detailService;

    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    @GetMapping
    public ResponseEntity<List<DetailResponse>> getAllDetails() {
        return ResponseEntity.ok(detailService.getAllDetails());
    }

    @PostMapping
    public ResponseEntity<DetailResponse> addDetail(@RequestBody DetailRequest request) {
        DetailResponse response = detailService.addDetail(request);
        return ResponseEntity.ok(response);
    }
}
