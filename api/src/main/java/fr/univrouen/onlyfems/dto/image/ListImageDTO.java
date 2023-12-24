package fr.univrouen.onlyfems.dto.image;

import java.util.List;

public class ListImageDTO {
    private final List<ImageDTO> images;

    public ListImageDTO(List<ImageDTO> images) {
        this.images = images;
    }

    public List<ImageDTO> getImages() {
        return images;
    }
}
