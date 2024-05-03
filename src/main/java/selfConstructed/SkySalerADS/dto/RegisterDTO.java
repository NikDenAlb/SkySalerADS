package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import selfConstructed.SkySalerADS.model.Role;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @Size(min = 3, max = 32, message = "The number must be at least 3 characters and no more than 32")
    private String username;
    @Size(min = 8, max = 16, message = "The number must be at least 8 characters and no more than 16")
    private String password;
    @Size(min = 2, max = 16, message = "The number must be at least 2 characters and no more than 16")
    private String firstName;
    @Size(min = 2, max = 16, message = "The number must be at least 2 characters and no more than 16")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Invalid characters in license plate")
    private String phone;
    private Role role;
}
