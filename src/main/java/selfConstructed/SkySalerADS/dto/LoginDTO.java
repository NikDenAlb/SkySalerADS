package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @Size(min = 4, max = 32, message = "The number must be at least 4 characters and no more than 32")
    private String username;
    @Size(min = 8, max = 16, message = "The number must be at least 8 characters and no more than 16")
    private String password;
}
