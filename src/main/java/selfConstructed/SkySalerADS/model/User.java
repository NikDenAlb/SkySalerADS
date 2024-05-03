package selfConstructed.SkySalerADS.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
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
    private byte[] image;
}
