package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель пользователя.
 */
@Data
@NoArgsConstructor
public class User {

    /**
     * User ID.
     */
    private Integer id;

    /**
     * User email.
     */
    private String email;

    /**
     *
     * Username.
     */
    private String firstName;

    /**
     * User's last name.
     */
    private String lastName;

    /**
     * User's phone number.
     */
    private String phone;

    /**
     * User role (for example, "USER").
     */
    private String role;

    /**
     * User image URL.
     */
    private String image;
}
