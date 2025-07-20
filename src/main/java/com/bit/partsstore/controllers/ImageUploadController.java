package com.bit.partsstore.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class ImageUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir; // uploads/

    @PostMapping("/upload/car")
    public ResponseEntity<String> uploadCarImage(@RequestParam("image") MultipartFile file) {
        return handleUpload(file, "cars");
    }

    @PostMapping("/upload/part")
    public ResponseEntity<String> uploadPartImage(@RequestParam("image") MultipartFile file) {
        return handleUpload(file, "parts");
    }

    /**
     * Handles the upload of a file to a specified subfolder within the configured upload directory.
     * Generates a unique filename, saves the file to disk, and returns a public path
     * that can be stored in the database and accessed from the frontend.
     *
     * @param file      the uploaded file to process
     * @param subfolder the subfolder within the upload directory to save the file
     * @return a ResponseEntity containing the public file path on success,
     *         or an error message on failure
     */
    private ResponseEntity<String> handleUpload(MultipartFile file, String subfolder) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        try {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path folderPath = Paths.get(uploadDir).resolve(subfolder);
            Files.createDirectories(folderPath); // создаёт подкаталог, если нет

            Path filePath = folderPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // путь, который сохранится в БД и будет использоваться на фронте
            String publicPath = "/uploads/" + subfolder + "/" + fileName;
            return ResponseEntity.ok(publicPath);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload error: " + e.getMessage());
        }
    }
}
