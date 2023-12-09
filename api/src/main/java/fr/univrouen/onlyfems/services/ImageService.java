package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.entities.Image;
import fr.univrouen.onlyfems.exceptions.StorageException;
import fr.univrouen.onlyfems.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final IStorageService storageService;

    @Autowired
    public ImageService(ImageRepository imageRepository, FileSystemStorageService storageService) {
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    @Transactional
    public Image saveImage(MultipartFile file) throws StorageException {
        if (file == null) {
            throw new IllegalArgumentException("No file given.");
        }

        if (isFileValid(file)) {
            Image image = new Image();
            image.setName(file.getOriginalFilename());

            Image newImage = imageRepository.save(image);
            storageService.store(file, getFileName(newImage));

            return newImage;
        } else {
            throw new IllegalArgumentException("File given is not an image.");
        }
    }

    /**
     * Private method to check multiple verifications related to a MultipartFile object.
     *
     * @param file File to check.
     * @return true if the file is valid, false otherwise.
     */
    private boolean isFileValid(MultipartFile file) {
        // Verify if file is an image and if file is not empty.
        if (!file.getName().equals("image") || file.isEmpty()) {
            return false;
        }

        // Check extension of the file.
        if (
                file.getContentType().equals("image/png")
                || file.getContentType().equals("image/jpg")
                || file.getContentType().equals("image/jpeg")
                || file.getContentType().equals("image/svg")
                || file.getContentType().equals("image/gif")
        ) {
            return true;
        }

        System.out.println(file.getContentType());
        return false;
    }

    /**
     * Private method to get the file name.
     *
     * @param image Image containing information.
     * @return The name of the file.
     */
    private String getFileName(Image image) {
        String fileName = image.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        fileName = fileName.substring(0, lastDotIndex ) + "_" + image.getId() + fileName.substring(lastDotIndex);
        return fileName;
    }
}