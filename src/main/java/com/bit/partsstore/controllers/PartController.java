package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.PartResponse;
import com.bit.partsstore.services.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class PartController {

    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping
    public ResponseEntity<List<PartResponse>> getParts() {
        List<PartResponse> parts = partService.getParts();
        return ResponseEntity.ok(parts);
    }
}
