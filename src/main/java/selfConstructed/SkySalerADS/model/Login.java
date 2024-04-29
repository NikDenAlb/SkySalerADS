package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for user login.
 */
@Data
@NoArgsConstructor
public class Login {

    /**
     * Username.
     */
    private String username;

    /**
     * User password.
     */
    private String password;
}