package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.PartRequest;
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
    public ResponseEntity<List<PartResponse>> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    @PostMapping
    public ResponseEntity<PartResponse> addPart(@RequestBody PartRequest request) {
        PartResponse response = partService.addPart(request);
        return ResponseEntity.ok(response);
    }
}
