package fr.univrouen.onlyfems.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    @Column(name = "encoded_image")
    private String encodedImage;

    private String description;

    private boolean privacy;

    public Image(Integer id, String name, String encodedImage, String description, boolean privacy) {
        this.id = id;
        this.name = name;
        this.encodedImage = encodedImage;
        this.description = description;
        this.privacy = privacy;
    }

    public Image() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    /*@Override
    public String toString() {
        String result = "Image [id=" + id + ", name=" + name + ", description=" + description + ", publicity=" + publicity + "]";
        return result;
    }*/
}
