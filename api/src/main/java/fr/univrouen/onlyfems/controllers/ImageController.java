package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.entities.Image;
import fr.univrouen.onlyfems.repositories.ImageRepository;
import fr.univrouen.onlyfems.constants.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional.*;

@RestController
@CrossOrigin("*")
public class ImageController {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    
    /**
     *  Récupérer toutes les images du portfolio 
     * ( Donc les images publiques et privées dans le cas où l'utilisateur est connecté )
     */
    @RequestMapping(
        value = APIEndpoints.IMAGES_URL,
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Iterable<Image>> getImages() {
        // // Si l'utilisateur à des privilèges ou est l'admin, on renvoie toutes les images
        // if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        //     return ResponseEntity.ok(imageRepository.findAll());
        // }
        // // Sinon, on renvoie seulement les images publiques
        // return ResponseEntity.ok(imageRepository.findByPublicity(true));

        // On stocke les images dans une variable
        Iterable<Image> images = imageRepository.findAll();      
        
        // Si il n'y a pas d'images, on renvoie une erreur
        if (images == null) {
            return ResponseEntity.notFound().build();
        }

        // On renvoie les images
        return ResponseEntity.ok(images);
    }

    // Récupérer une image précise en fonction de son id
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Image> getImage(@PathVariable Integer id) {
        Image image = imageRepository.findById(id).orElse(null);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(image);
    }

    // Ajouter une image au portfolio
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL,
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Image> addImage(@RequestBody Image image) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        // if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
        //     return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        // }

        // On ajoute l'image à la base de données
        imageRepository.save(image);

        return ResponseEntity.ok(image);
    }

    // Retirer une image du portfolio
    @RequestMapping(
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
    }

    @RequestMapping(
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
        imageToUpdate.setIsPublic(image.getIsPublic());

        // On met à jour l'image dans la base de données
        imageRepository.save(imageToUpdate);

        return ResponseEntity.ok(imageToUpdate);
    }

}