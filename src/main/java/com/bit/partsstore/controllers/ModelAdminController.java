package com.bit.partsstore.controllers;

import com.bit.partsstore.DTO.ModelRequest;
import com.bit.partsstore.DTO.ModelResponse;
import com.bit.partsstore.models.Model;
import com.bit.partsstore.services.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/models")
public class ModelAdminController {

    private final ModelService modelService;

    public ModelAdminController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping
    public ResponseEntity<List<ModelResponse>> getModels() {
        List<ModelResponse> models = modelService.getModels();
        return ResponseEntity.ok(models);
    }

    @PostMapping
    public ResponseEntity<ModelResponse> addModel(@RequestBody ModelRequest modelRequest) {
        ModelResponse response = modelService.addModel(modelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Model> deleteModel(@PathVariable int id) {
        Model deletedModel = modelService.deleteModel(id);
        return ResponseEntity.ok(deletedModel);
    }

}
