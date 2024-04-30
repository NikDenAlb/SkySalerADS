package selfConstructed.SkySalerADS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import selfConstructed.SkySalerADS.model.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
