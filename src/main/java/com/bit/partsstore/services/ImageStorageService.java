package com.bit.partsstore.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service responsible for storing car images on the local file system.
 * Saves uploaded image files to a predefined directory for cars.
 */
@Service
public class ImageStorageService {

    private final Path uploadPath = Paths.get("uploads/cars");

    /**
     * Saves the uploaded car image to the configured upload directory.
     * Creates the directory if it does not exist, and replaces the file if it already exists.
     *
     * @param imageFile the image file to be saved
     * @param filename  the name under which the file should be saved
     * @throws IOException if an I/O error occurs during saving
     * @throws RuntimeException if saving fails for any reason
     */
    public void saveCarImage(MultipartFile imageFile, String filename) throws IOException {
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path targetPath = uploadPath.resolve(filename);
            Files.copy(imageFile.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save car image", e);
        }
    }
}
