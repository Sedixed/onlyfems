package fr.univrouen.onlyfems.dto.image;


public class UploadImageDTO {

    private String fileName;
    private String description;
    private Boolean privacy;
    private String contentType;
    private  String base64;

    public UploadImageDTO(String fileName, String description, Boolean privacy, String contentType, String base64) {
        this.fileName = fileName;
        this.description = description;
        this.privacy = privacy;
        this.contentType = contentType;
        this.base64 = base64;
    }

    //public UploadImageDTO() {}

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
