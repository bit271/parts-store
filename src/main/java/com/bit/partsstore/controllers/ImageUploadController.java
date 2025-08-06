package com.bit.partsstore.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/upload/car/{filename:.+}")
    public ResponseEntity<Resource> getCarImage(@PathVariable String filename) {
        return getImageFrom(filename, "cars");
    }

    @GetMapping("/upload/part/{filename:.+}")
    public ResponseEntity<Resource> getPartImage(@PathVariable String filename) {
        return getImageFrom(filename, "parts");
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

    /**
     * Retrieves an image file from the specified subfolder and returns it as a response entity.
     * The method ensures safe access within the upload directory and validates the existence of the requested file.
     *
     * @param filename the name of the image file to retrieve
     * @param subfolder the subfolder within the upload directory where the file is located
     * @return a ResponseEntity containing the requested image resource if found,
     *         a bad request response if access validation fails,
     *         not found response if the file does not exist,
     *         or an internal server error response in case of other errors
     */
    private ResponseEntity<Resource> getImageFrom(String filename, String subfolder) {
        try {
            Path dir = Paths.get(uploadDir).resolve(subfolder);
            Path filePath = dir.resolve(filename).normalize();

            // Защита от выхода за пределы каталога (безопасность)
            if (!filePath.startsWith(dir)) {
                return ResponseEntity.badRequest().build();
            }

            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            String contentType = Files.probeContentType(filePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

}
