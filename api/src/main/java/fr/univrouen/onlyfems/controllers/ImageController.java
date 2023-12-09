package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.dto.DTO;
import fr.univrouen.onlyfems.dto.error.ErrorDTO;
import fr.univrouen.onlyfems.dto.image.ImageDTO;
import fr.univrouen.onlyfems.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import fr.univrouen.onlyfems.constants.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }
    
    /**
     *  Retrieve all images.
     */
    /*@RequestMapping(
        value = APIEndpoints.IMAGES_URL,
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Iterable<Image>> getAllImages() {
        // On stocke les images dans une variable
        // Si il n'y a pas d'images, on renvoie une erreur
        //return ResponseEntity.notFound().build();

        // On renvoie les images
        //return ResponseEntity.ok(images);

    }*/

    // Récupérer une image précise en fonction de son id
    /*@RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        //Image image = imageRepository.findById(id).orElse(null);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(image);
    }*/

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

    // Retirer une image du portfolio
    /*@RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Image> deleteImage(@PathVariable Integer id) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        // if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // }

        // On récupère l'image
        Image image = imageRepository.findById(id).orElse(null);

        // Si l'image n'existe pas, on renvoie une erreur
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        // On supprime l'image de la base de données
        imageRepository.deleteById(image.getId());

        return ResponseEntity.ok(image);
    }*/

    /*@RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.PUT,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Image> updateImage(@PathVariable Integer id, @RequestBody Image image) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        // if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // }

        // On récupère l'image
        Image imageToUpdate = imageRepository.findById(id).orElse(null);

        // Si l'image n'existe pas, on renvoie une erreur
        if (imageToUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        // On met à jour l'image
        imageToUpdate.setName(image.getName());
        imageToUpdate.setEncodedImage(image.getEncodedImage());
        imageToUpdate.setDescription(image.getDescription());
        imageToUpdate.setPublicity(image.getPublicity());

        // On met à jour l'image dans la base de données
        imageRepository.save(imageToUpdate);

        return ResponseEntity.ok(imageToUpdate);
    }*/

}