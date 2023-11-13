package fr.univrouen.onlyfems.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String encodedImage;

    private String description;

    private Bool isPublic;

    public Image(
        String name, 
        String encodedImage, 
        String description, 
        Bool isPublic
    ) {
        this.name = name;
        this.encodedImage = encodedImage;
        this.description = description;
        this.isPublic = isPublic;
    }

    public Image() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bool getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Bool isPublic) {
        this.isPublic = isPublic;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "Image [id=" + id + ", name=" + name + ", description=" + description + ", isPublic=" + isPublic + "]";
    }
}
