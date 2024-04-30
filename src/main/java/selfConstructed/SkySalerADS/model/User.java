package selfConstructed.SkySalerADS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * User ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    /**
     * User email.
     */
    private String email;

    /**
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
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    /**
     * User image URL.
     */
    private String image;
}
