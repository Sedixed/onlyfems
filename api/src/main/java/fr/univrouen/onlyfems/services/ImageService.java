package fr.univrouen.onlyfems.services;

import fr.univrouen.onlyfems.entities.Image;
import fr.univrouen.onlyfems.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Image saveImage(MultipartFile file) {
        Image image = new Image();
        image.setName(file.getOriginalFilename());

        // BASE64Decoder decoder = new BASE64Decoder();
        // byte[] imageByte = decoder.decodeBuffer(imageData);

        // image.setEncodedImage(Base64.getEncoder().encode(file.getBytes()));
        image.setDescription("Description");
        image.setPublicity(true);

        return imageRepository.save(image);
    }
}