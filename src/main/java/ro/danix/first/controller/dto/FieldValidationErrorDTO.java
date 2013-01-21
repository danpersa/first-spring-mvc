package ro.danix.first.controller.dto;

/**
 * @author danix
 */
public class FieldValidationErrorDTO {

    private String path;
    private String message;

    public FieldValidationErrorDTO(String path, String message) {
        this.path = path;
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}
