package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.dto.image.ImageDTO;
import fr.univrouen.onlyfems.dto.image.ListImageDTO;
import fr.univrouen.onlyfems.dto.image.UploadImageDTO;
import fr.univrouen.onlyfems.entities.Image;
import fr.univrouen.onlyfems.exceptions.StorageException;
import fr.univrouen.onlyfems.exceptions.StorageFileNotFoundException;
import fr.univrouen.onlyfems.repositories.ImageRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final IStorageService storageService;

    @Autowired
    public ImageService(ImageRepository imageRepository, FileSystemStorageService storageService) {
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    /**
     * Find an image in database using its ID.
     *
     * @param id ID of the image.
     * @return The image found.
     */
    public ImageDTO findById(int id) throws ObjectNotFoundException, StorageFileNotFoundException, IOException {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            Resource imageResource = storageService.loadAsResource(getFileName(image));
            image.setBase64(Files.readAllBytes(imageResource.getFile().toPath()));
            return new ImageDTO(image);
        } else {
            throw new ObjectNotFoundException("Image not found in database", id);
        }
    }

    /**
     * Find all the images in database.
     *
     * @return The list of ImageDTO found.
     */
    public ListImageDTO findALl() throws StorageFileNotFoundException, IOException {
        List<ImageDTO> result = new ArrayList<>();

        Resource imageResource;
        for (Image image : imageRepository.findAll()) {
            imageResource = storageService.loadAsResource(getFileName(image));
            image.setBase64(Files.readAllBytes(imageResource.getFile().toPath()));
            result.add(new ImageDTO(image));
        }
        return new ListImageDTO(result);
    }

    /**
     * Create an image in database and in upload/ directory.
     *
     * @param saveRequest Request containing image information and file.
     * @return The image saved.
     */
    @Transactional
    public ImageDTO saveImage(UploadImageDTO saveRequest) throws StorageException, IOException {
        Image image = new Image(saveRequest.getName(), saveRequest.getDescription(), saveRequest.getPrivacy(), saveRequest.getContentType(), saveRequest.getBase64());
        if (isFileValid(image)) {

            Image newImage = imageRepository.save(image);
            storageService.store(image, getFileName(newImage));

            Resource imageResource = storageService.loadAsResource(getFileName(newImage));
            image.setBase64(Files.readAllBytes(imageResource.getFile().toPath()));
            return new ImageDTO(newImage);
        } else {
            throw new IllegalArgumentException("File given is not an image.");
        }
    }

    /**
     * Update the image in database and the file.
     *
     * @param updateRequest Request containing image information and file.
     * @param id ID of the image to update.
     * @return The image updated.
     */
    @Transactional
    public ImageDTO updateImage(UploadImageDTO updateRequest, int id) throws StorageException, IOException {
        Image image = imageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Image not found in database", id));

        // If base 64 content has not changed.
        if (updateRequest.getBase64() == null || updateRequest.getBase64().equals("")) {

            // If file name has changed.
            if (updateRequest.getName() != null) {

                Resource oldImageResource = storageService.loadAsResource(getFileName(image));
                String oldFileName = image.getName();

                image.setName(updateRequest.getName());
                image.setDescription(updateRequest.getDescription());
                image.setPrivacy(updateRequest.getPrivacy());
                image.setContentType(updateRequest.getContentType());
                image.setBase64(oldImageResource.getContentAsByteArray());

                if (isFileValid(image)) {
                    storageService.store(image, getFileName(image));
                    Image updatedImage = imageRepository.save(image);

                    Image imageToDelete = new Image(oldFileName, image.getDescription(), image.getPrivacy(), image.getContentType(), image.getBase64String());
                    imageToDelete.setId(updatedImage.getId());
                    storageService.delete(getFileName(imageToDelete));

                    return new ImageDTO(updatedImage);
                } else {
                    throw new IllegalArgumentException("File given is not an image.");
                }
            // File name has not changed.
            } else {

                image.setDescription(updateRequest.getDescription());
                image.setPrivacy(updateRequest.getPrivacy());
                image.setContentType(updateRequest.getContentType());
                Image updatedImage = imageRepository.save(image);
                Resource imageResource = storageService.loadAsResource(getFileName(image));
                updatedImage.setBase64(Files.readAllBytes(imageResource.getFile().toPath()));

                return new ImageDTO(updatedImage);
            }
        // Bytes has changed.
        } else {
            String oldFileName = image.getName();

            image.setName(updateRequest.getName());
            image.setDescription(updateRequest.getDescription());
            image.setPrivacy(updateRequest.getPrivacy());
            image.setContentType(updateRequest.getContentType());
            image.setBase64(updateRequest.getBase64());

            if (isFileValid(image)) {
                storageService.store(image, getFileName(image));
            } else {
                throw new IllegalArgumentException("File given is not an image.");
            }
            Image updatedImage = imageRepository.save(image);

            // If file name has changed.
            if (updateRequest.getName() != null && !oldFileName.equals(updateRequest.getName())) {
                Image imageToDelete = new Image(oldFileName, image.getDescription(), image.getPrivacy(), image.getContentType(), image.getBase64String());
                imageToDelete.setId(updatedImage.getId());
                storageService.delete(getFileName(imageToDelete));
            }
            return new ImageDTO(updatedImage);
        }
    }

    /**
     * Delete the image using its ID.
     *
     * @param id ID of the image to delete.
     */
    @Transactional
    public void deleteImage(int id) throws StorageException {
        Optional<Image> imageOptional = imageRepository.findById(id);

        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            imageRepository.delete(image);
            storageService.delete(getFileName(image));
        } else {
            throw new ObjectNotFoundException("Image not found in database", id);
        }
    }

    /**
     * Private method to check multiple verifications related to a MultipartFile object.
     *
     * @param file File to check.
     * @return true if the file is valid, false otherwise.
     */
    private boolean isFileValid(MultipartFile file) {
        // Check if file is not empty.
        if (file.isEmpty()) {
            return false;
        }
        // Check extension of the file.
        return file.getContentType().equals("image/png")
                || file.getContentType().equals("image/jpg")
                || file.getContentType().equals("image/jpeg")
                || file.getContentType().equals("image/svg")
                || file.getContentType().equals("image/gif");
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