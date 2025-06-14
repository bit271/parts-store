package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.ModelResponse;
import com.bit.partsstore.services.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getModels() {
        List<ModelResponse> models = modelService.getModels();
        return ResponseEntity.ok(models);
    }
}


