package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * For user Update
 * @field firstName
 * @field lastName
 * @field phone
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    @Size(min = 3, max = 10, message = "The number must be at least 3 characters and no more than 10")
    private String firstName;
    @Size(min = 3, max = 10, message = "The number must be at least 3 characters and no more than 10")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid characters in license plate")
    private String phone;
}
