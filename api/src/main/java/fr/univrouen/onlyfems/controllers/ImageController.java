package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.dto.DTO;
import fr.univrouen.onlyfems.dto.error.ErrorDTO;
import fr.univrouen.onlyfems.dto.image.ImageDTO;
import fr.univrouen.onlyfems.services.ImageService;
import org.springframework.web.multipart.MultipartFile;
import fr.univrouen.onlyfems.constants.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Find an image using its ID.
     *
     * @param id ID of the image.
     * @return The image found.
     */
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DTO> getImage(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new ImageDTO(imageService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    /**
     *  Retrieve all images.
     */
    @RequestMapping(
            value = APIEndpoints.IMAGES_URL,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> getAllImages() {
        try {
            return ResponseEntity.ok(imageService.findALl());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    /**
     * Upload an image on the API.
     *
     * @param file Image to upload.
     * @return The image uploaded.
     */
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL,
        method = RequestMethod.POST,
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DTO> uploadImage(@RequestPart("image") MultipartFile file) {
        try {
            ImageDTO imageDTO = new ImageDTO(imageService.saveImage(file));
            return ResponseEntity.ok(imageDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    /**
     * Update an image.
     *
     * @param file Uploaded file.
     * @param id ID of the file to update.
     * @return The image DTO of the updated image.
     */
    @RequestMapping(
            value = APIEndpoints.IMAGE_URL + "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<DTO> updateImage(@RequestPart("image") MultipartFile file, @PathVariable Integer id) {
        try {
            ImageDTO imageDTO = new ImageDTO(imageService.updateImage(file, id));
            return ResponseEntity.ok(imageDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

    /**
     * Delete an image.
     *
     * @param id ID of the image to delete.
     * @return 204.
     */
    @RequestMapping(
            value = APIEndpoints.IMAGE_URL + "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> deleteImage(@PathVariable Integer id) {
        try {
            imageService.deleteImage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage()));
        }
    }

}