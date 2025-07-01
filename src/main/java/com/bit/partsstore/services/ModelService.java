package com.bit.partsstore.services;

import com.bit.partsstore.DTO.ModelRequest;
import com.bit.partsstore.DTO.ModelResponse;
import com.bit.partsstore.models.Model;
import com.bit.partsstore.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {

    private static final String MODEL_ALREADY_EXISTS = "Model already exists";
    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<ModelResponse> getModels() {
        return modelRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ModelResponse addModel(ModelRequest request) {
        if (modelRepository.existsByName(request.getName())) {
            throw new RuntimeException(MODEL_ALREADY_EXISTS);
        }
        Model model = createModelFromRequest(request);
        Model saved = modelRepository.save(model);
        return mapToResponse(saved);
    }

    private Model createModelFromRequest(ModelRequest request) {
        Model model = new Model();
        model.setName(request.getName());
        return model;
    }

    private ModelResponse mapToResponse(Model model) {
        return new ModelResponse(model.getId(), model.getName());
    }
}
