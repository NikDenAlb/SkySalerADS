package selfConstructed.SkySalerADS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Model for user registration.
 */
@Data
@NoArgsConstructor
@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
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
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private String role;
}
