package selfConstructed.SkySalerADS.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUser {
    @Size(min = 3, max = 10, message = "The number must be at least 3 characters and no more than 10")
    private String firstname;
    @Size(min = 3, max = 10, message = "The number must be at least 3 characters and no more than 10")
    private String lastname;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid characters in license plate")
    private String phone;
}
