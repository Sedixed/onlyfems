package fr.univrouen.onlyfems.controllers;

import fr.univrouen.onlyfems.entities.Image;
import fr.univrouen.onlyfems.repositories.ImageRepository;
import fr.univrouen.onlyfems.constants.APIEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin("*")
public class ImageController {

    /**
     *  Récupérer toutes les images du portfolio 
     * ( Donc les images publiques et privées dans le cas où l'utilisateur est connecté )
     */
    @RequestMapping(
        value = APIEndpoints.IMAGES_URL,
        method = RequestMethod.GET
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getImages() {
        // Si l'utilisateur à des privilèges ou est l'admin, on renvoie toutes les images
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return ResponseEntity.ok(imageRepository.findAll());
        }
        // Sinon, on renvoie seulement les images publiques
        return ResponseEntity.ok(imageRepository.findByPublicity(true));
    }

    // Récupérer une image précise en fonction de son id
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.GET
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> getImage(@PathVariable Integer id) {
        Image image = imageRepository.findById(id);

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(image);
    }

    // Ajouter une image au portfolio
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL,
        method = RequestMethod.POST
        consumes = MediaType.APPLICATION_JSON_VALUE
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> addImage(@RequestBody Image image) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // On ajoute l'image à la base de données
        imageRepository.save(image);

        return ResponseEntity.ok(image);
    }

    // Retirer une image du portfolio
    @RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.DELETE
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteImage(@PathVariable Integer id) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // On récupère l'image
        Image image = imageRepository.findById(id);

        // Si l'image n'existe pas, on renvoie une erreur
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        // On supprime l'image de la base de données
        imageRepository.deleteById(Image.getId());

        return ResponseEntity.ok(image);
    }

    @RequestMapping(
        value = APIEndpoints.IMAGE_URL + "/{id}",
        method = RequestMethod.PUT
        consumes = MediaType.APPLICATION_JSON_VALUE
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> updateImage(@PathVariable Integer id, @RequestBody Image image) {
        // Si l'utilisateur n'est pas connecté, on renvoie une erreur
        if (!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // On récupère l'image
        Image image = imageRepository.findById(id);

        // Si l'image n'existe pas, on renvoie une erreur
        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        // On met à jour l'image dans la base de données
        imageRepository.save(image);

        return ResponseEntity.ok(image);
    }

}