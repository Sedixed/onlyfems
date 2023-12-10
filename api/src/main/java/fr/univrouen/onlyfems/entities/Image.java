package fr.univrouen.onlyfems.entities;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name = "";

    private String description = "";

    // true : public | false : private
    private boolean privacy = true;

    @Transient
    private String base64Encoded = null;

    public Image(String name, String description, boolean privacy) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String getBase64Encoded() {
        return base64Encoded;
    }
    public void setBase64Encoded(String base64Encoded) {
        this.base64Encoded = base64Encoded;
    }
}
