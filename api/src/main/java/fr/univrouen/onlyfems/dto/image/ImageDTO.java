package fr.univrouen.onlyfems.dto.image;

import fr.univrouen.onlyfems.dto.DTO;
import fr.univrouen.onlyfems.entities.Image;

/**
 * DTO used to transfer images data.
 */
public class ImageDTO implements DTO {
    private final Integer id;

    private final String name;

    private final String description;

    private final boolean privacy;

    private final String base64Encoded;

    public ImageDTO(Integer id, String name, String description, boolean privacy, String base64Encoded) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.privacy = privacy;
        this.base64Encoded = base64Encoded;
    }

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.name = image.getName();
        this.description = image.getDescription();
        this.privacy = image.isPrivate();
        this.base64Encoded = image.getBase64Encoded();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPublic() {
        return privacy;
    }

    public String getBase64Encoded() {
        return base64Encoded;
    }
}
