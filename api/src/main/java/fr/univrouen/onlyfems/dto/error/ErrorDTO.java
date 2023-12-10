package fr.univrouen.onlyfems.dto.error;

import fr.univrouen.onlyfems.dto.DTO;

public class ErrorDTO implements DTO {
    String error;

    public ErrorDTO(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
