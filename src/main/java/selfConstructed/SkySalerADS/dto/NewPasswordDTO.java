package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {
    @Size(min = 2, max = 16, message = "The number must be at least 8 characters and no more than 16")
    private String currentPassword;
    @Size(min = 2, max = 16, message = "The number must be at least 8 characters and no more than 16")
    private String newPassword;
}
