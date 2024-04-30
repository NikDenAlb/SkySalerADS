package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model for user registration.
 */
@Data
@NoArgsConstructor
public class Register {

    /**
     * Username.
     */
    private String username;

    /**
     * User password.
     */
    private String password;

    /**
     * Username.
     */
    private String firstName;

    /**
     * User last name.
     */
    private String lastName;

    /**
     * User's phone number.
     */
    private String phone;

    /**
     * User role.
     */
    private String role;
}
