package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.dto.image.ImageDTO;
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
import java.util.Base64;
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
     * @throws ObjectNotFoundException
     * @throws StorageFileNotFoundException
     * @throws IOException
     */
    public Image findById(int id) throws ObjectNotFoundException, StorageFileNotFoundException, IOException {
        Optional<Image> optionalImage = imageRepository.findById(id);
        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            Resource imageResource = storageService.loadAsResource(getFileName(image));
            image.setBase64Encoded(getBase64Encoded(imageResource));
            return image;
        } else {
            throw new ObjectNotFoundException("Image not found in database", id);
        }
    }

    /**
     * Find all the images in database.
     *
     * @return The list of ImageDTO found.
     * @throws StorageFileNotFoundException
     * @throws IOException
     */
    public List<ImageDTO> findALl() throws StorageFileNotFoundException, IOException {
        List<ImageDTO> result = new ArrayList<>();

        Resource imageResource;
        for (Image image : imageRepository.findAll()) {
            imageResource = storageService.loadAsResource(getFileName(image));
            image.setBase64Encoded(getBase64Encoded(imageResource));
            result.add(new ImageDTO(image));
        }
        return result;
    }

    /**
     * Create an image in database and in upload/ directory.
     *
     * @param saveRequest Request containing image information and file.
     * @return The image saved.
     * @throws StorageException
     */
    @Transactional
    public Image saveImage(UploadImageDTO saveRequest) throws StorageException, IOException {
        MultipartFile file = saveRequest.getFile();
        if (isFileValid(file)) {
            Image image = new Image();
            image.setName(file.getOriginalFilename());

            image.setDescription(saveRequest.getDescription());
            image.setPrivacy(saveRequest.isPrivacy());

            Image newImage = imageRepository.save(image);
            storageService.store(file, getFileName(newImage));

            Resource imageResource = storageService.loadAsResource(getFileName(newImage));
            image.setBase64Encoded(getBase64Encoded(imageResource));
            return newImage;
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
     * @throws StorageException
     * @throws IOException
     */
    @Transactional
    public Image updateImage(UploadImageDTO updateRequest, int id) throws StorageException, IOException {
        MultipartFile file = updateRequest.getFile();
        if (isFileValid(file)) {
            Optional<Image> imageOptional = imageRepository.findById(id);

            if (imageOptional.isPresent()) {
                Image image = imageOptional.get();

                image.setDescription(updateRequest.getDescription());
                image.setPrivacy(updateRequest.isPrivacy());

                imageRepository.save(image);
                storageService.store(file, getFileName(image));

                Resource imageResource = storageService.loadAsResource(getFileName(image));

                image.setBase64Encoded(getBase64Encoded(imageResource));
                return image;
            } else {
                throw new ObjectNotFoundException("Image not found in database", id);
            }
        } else {
            throw new IllegalArgumentException("File given is not an image.");
        }
    }

    /**
     * Delete the image using its ID.
     *
     * @param id ID of the image to delete.
     * @throws StorageException
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
        // Verify if file is not empty.

        if (file.isEmpty()) {
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

    /**
     * Private method to get an image encoded in base 64.
     *
     * @param resource Image resource.
     * @return The base 64 encoded image.
     * @throws IOException
     */
    private String getBase64Encoded(Resource resource) throws IOException {
        byte[] fileContent = Files.readAllBytes(resource.getFile().toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}