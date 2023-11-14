package fr.univrouen.onlyfems.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    
    @Lob
    @Column(name = "encoded_image")
    private byte[] encodedImage;

    private String description;

    private boolean publicity;

    public Image(
        String name, 
        byte[] encodedImage, 
        String description, 
        boolean publicity
    ) {
        this.name = name;
        this.encodedImage = encodedImage;
        this.description = description;
        this.publicity = publicity;
    }

    public Image() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getPublicity() {
        return publicity;
    }

    public void setPublicity(boolean publicity) {
        this.publicity = publicity;
    }

    public byte[] getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(byte[] encodedImage) {
        this.encodedImage = encodedImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public String toString() {
        String result = "Image [id=" + id + ", name=" + name + ", description=" + description + ", publicity=" + publicity + "]";
        return result;
    }
}
