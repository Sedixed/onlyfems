package fr.univrouen.onlyfems.dto.image;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageDTO {
    private String description = "";
    private boolean privacy = true;
    private MultipartFile file;

    public UploadImageDTO(String description, boolean privacy, MultipartFile file) {
        this.description = description;
        this.privacy = privacy;
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public MultipartFile getFile() {
        return file;
    }
}
